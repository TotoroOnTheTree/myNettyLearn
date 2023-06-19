package com.xm.im.server;

import com.xm.im.client.handler.ClientCmdHandler;
import com.xm.im.handler.PacketDecodeHandler;
import com.xm.im.handler.PacketEncodeHandler;
import com.xm.im.handler.PacketSimpleHandler;
import com.xm.im.handler.PacketSplitHandler;
import com.xm.im.server.handler.ServerCmdHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public class MyImServer {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(boss,worker)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline()
                        .addLast(new PacketSplitHandler())
                        .addLast(new PacketDecodeHandler())
                        .addLast(new ServerCmdHandler())
                        .addLast(new PacketSimpleHandler())
                        .addLast(new PacketEncodeHandler())
                    ;
                }
            })
            .bind("127.0.0.1",11000)
            .addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("服务端启动成功");
                    }else {
                        System.out.println("服务端启动失败");
                    }
                }
            });
    }
}
