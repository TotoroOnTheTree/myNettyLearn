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
        super(Integer.MAX_VALUE, 9, 4);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf packet = (ByteBuf) super.decode(ctx,in);
        if (packet != null) {
            packet.markReaderIndex();
            int magic = in.readInt();
            if (Packet.MAGIC != magic) {
                System.out.println("非协议请求，关闭连接");
                ctx.channel().close();
            }
            packet.resetReaderIndex();
        }
        return packet;
    }
}
