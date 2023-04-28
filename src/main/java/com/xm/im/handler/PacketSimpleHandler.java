package com.xm.im.handler;

import com.xm.im.protol.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/28
 * @since 1.0
 **/
public class PacketSimpleHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        System.out.println("收到消息："+String.valueOf(msg.getData()));
    }
}
