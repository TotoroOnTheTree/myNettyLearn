package com.xm.im.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/28
 * @since 1.0
 **/
public class FirstChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+"：客户端写出数据");
        ByteBuf buffer = ctx.alloc().buffer();
        String msg = "你好";
        buffer.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
