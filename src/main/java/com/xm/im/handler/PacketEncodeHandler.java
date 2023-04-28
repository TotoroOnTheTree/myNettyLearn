package com.xm.im.handler;

import com.xm.im.protol.Packet;
import com.xm.im.protol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/25
 * @since 1.0
 **/
public class PacketEncodeHandler extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.encode(out,msg);
    }
}
