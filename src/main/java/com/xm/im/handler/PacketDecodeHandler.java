package com.xm.im.handler;

import com.xm.im.command.CommandFactory;
import com.xm.im.protol.Packet;
import com.xm.im.protol.PacketCodec;
import com.xm.im.serializer.SerializeFactory;
import com.xm.im.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/25
 * @since 1.0
 **/
public class PacketDecodeHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Packet packet = PacketCodec.decode(in);
        //获取序列化对象
        Serializer serializer = SerializeFactory.get(packet.getSerializeType());
        //获取命令类型
        Class commandClazz = CommandFactory.getCommand(packet.getCommand());
        if(commandClazz==null || serializer == null){
            return;
        }
        //转为命令对象
        Object command = serializer.deserialize(packet.getData(), commandClazz);
        out.add(command);
    }
}
