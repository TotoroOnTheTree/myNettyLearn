package com.xm.im.client;

import com.xm.im.client.handler.ClientCmdHandler;
import com.xm.im.cmd.CmdTypeEnum;
import com.xm.im.cmd.entity.HeartBeat;
import com.xm.im.handler.PacketDecodeHandler;
import com.xm.im.handler.CmdEncodeHandler;
import com.xm.im.handler.PacketEncodeHandler;
import com.xm.im.handler.PacketSplitHandler;
import com.xm.im.protol.Packet;
import com.xm.im.protol.PacketCreator;
import com.xm.im.serializer.SerializeTypeEnum;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public class MyImClient {

    private final static int MAX_RETRY = 5;
    private final static int HEART_DELAY = 15;

    private static Channel clientChannel = null;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(worker)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline()
                        .addLast(new PacketSplitHandler())
                        .addLast(new PacketDecodeHandler())
                        .addLast(new ClientCmdHandler())
                        .addLast(new CmdEncodeHandler())
                        .addLast(new PacketEncodeHandler())
                    ;
                }
            });
        connectRetry(bootstrap, "127.0.0.1",11000,5);
    }


    private static void connectRetry(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接服务端成功");
                ChannelFuture channelFuture = (ChannelFuture) future;
                clientChannel = channelFuture.channel();
                connectSuccess();
            } else if (retry == 0) {
                System.out.println("找不到服务器，关闭客户端");
                System.exit(0);
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.out.println("连接失败，第" + order + "次重连");
                bootstrap.config().group().schedule(() -> connectRetry(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }


    public static void connectSuccess(){
        //建立心跳
        heartBeat(HEART_DELAY);

        //启动输入监听
        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请输入内容：");
                String msg = scanner.nextLine();
                byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
                Packet packet = PacketCreator.create(SerializeTypeEnum.UNKNOWN.getCode(), CmdTypeEnum.STRING.getType(),bytes);
                //输入内容当做文本传递
                clientChannel.writeAndFlush(packet);
            }
        });
        thread.start();
    }

    private static void heartBeat(int delay){
        //建立心跳
        clientChannel.eventLoop().schedule(() -> {
            if (clientChannel.isActive()) {
                System.out.println("发送心跳");
                HeartBeat heartBeat = new HeartBeat("xm");
                clientChannel.writeAndFlush(heartBeat);
                //再次注册心跳
                heartBeat(HEART_DELAY);
            }
        }, delay, TimeUnit.SECONDS);
    }
}
