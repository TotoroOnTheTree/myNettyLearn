package com.xm.im.handler;

import com.xm.im.protol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public class PacketSplitHandler extends LengthFieldBasedFrameDecoder {

    public PacketSplitHandler() {
        super(Integer.MAX_VALUE, 10, 4);
    }
}
