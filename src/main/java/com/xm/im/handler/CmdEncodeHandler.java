package com.xm.im.handler;

import com.xm.im.cmd.entity.CmdPacket;
import com.xm.im.protol.Packet;
import com.xm.im.protol.PacketCodec;
import com.xm.im.protol.PacketCreator;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/25
 * @since 1.0
 **/
public class CmdEncodeHandler extends MessageToByteEncoder<CmdPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CmdPacket cmd, ByteBuf out) throws Exception {
        Packet packet = PacketCreator.createByJson(cmd.getCommandType(), cmd);
        PacketCodec.encode(out,packet);
    }
}
