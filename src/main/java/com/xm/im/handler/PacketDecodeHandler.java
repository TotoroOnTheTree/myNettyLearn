package com.xm.im.handler;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.CommandType;
import com.xm.im.protol.Packet;
import com.xm.im.protol.PacketCodec;
import com.xm.im.serializer.SerializeFactory;
import com.xm.im.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.nio.charset.StandardCharsets;
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
        if (Packet.MAGIC != packet.getMagic()) {
            System.out.println("非协议请求，关闭连接");
            ctx.channel().close();
        }
        //获取序列化对象
        Serializer serializer = SerializeFactory.get(packet.getSerializeType());
        //获取命令类型
        Class commandClazz = CommandType.getCommand(packet.getCommand());
        //找不到命令的转为字符串
        if(commandClazz==null || serializer == null){
            out.add(new String(packet.getData(), StandardCharsets.UTF_8));
            return;
        }
        //转为命令对象
        Object command = serializer.deserialize(packet.getData(), commandClazz);
        CmdEntity cmd = CmdEntity.builder().commandType(packet.getCommand())
            .commandClazz(commandClazz)
            .commandMsg(command)
            .build();
        out.add(cmd);
    }


}
