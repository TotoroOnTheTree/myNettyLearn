package com.xm.im.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/28
 * @since 1.0
 **/
public class FirstServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date()+"：服务端读到的数据："+buf.toString(StandardCharsets.UTF_8));
    }
}
