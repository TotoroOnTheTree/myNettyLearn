package com.xm.im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/28
 * @since 1.0
 **/
public class TestInbound extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        super.channelRead(ctx, msg);

    }
}
