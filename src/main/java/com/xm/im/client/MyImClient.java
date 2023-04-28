package com.xm.im.client;

import com.xm.im.client.handler.FirstChannelHandler;
import com.xm.im.handler.PacketDecodeHandler;
import com.xm.im.handler.PacketEncodeHandler;
import com.xm.im.handler.PacketSimpleHandler;
import com.xm.im.handler.PacketSplitHandler;
import com.xm.im.handler.TestInbound;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public class MyImClient {


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(worker)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline()
//                        .addLast(new StringEncoder())
                        .addLast(new PacketSplitHandler())
                        .addLast(new PacketDecodeHandler())
//                        .addLast(new FirstChannelHandler())
                        .addLast(new PacketSimpleHandler())
                        .addLast(new PacketEncodeHandler())
                    ;
                }
            });
        bootstrap.connect("127.0.0.1", 11000)
            .addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("连接服务端成功");
                    ChannelFuture channelFuture = (ChannelFuture) future;
                    Channel channel = channelFuture.channel();
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("请输入内容：");
                    String msg = scanner.nextLine();
                    ByteBuf buffer = channel.alloc().buffer();
                    buffer.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
                    channel.writeAndFlush(buffer);
                } else {
                    System.out.println("连接服务器失败");
                }
            });
    }

}
