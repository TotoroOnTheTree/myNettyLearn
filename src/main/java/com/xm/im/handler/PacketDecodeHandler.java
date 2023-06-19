package com.xm.im.handler;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.CmdTypeEnum;
import com.xm.im.protol.Packet;
import com.xm.im.protol.PacketCodec;
import com.xm.im.serializer.SerializeFactory;
import com.xm.im.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

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
        CmdTypeEnum cmdType = CmdTypeEnum.getCommand(packet.getCommand());
        //找不到类型不处理
        if(cmdType==null && serializer == null){
            System.out.println("找不到类型不处理");
            return;
        }
        //空包，将packet交给其他需要处理的handler
        if (cmdType.isEmpty()) {
            out.add(packet);
            return;
        }

        CmdEntity cmd=null;
        
        //如果是文本消息则不序列化
        if (cmdType.isStr()) {
            cmd = CmdEntity.builder().commandType(packet.getCommand())
                .commandClazz(cmdType.getClazz())
                .commandMsg(new String(packet.getData(),StandardCharsets.UTF_8))
                .build();
            out.add(cmd);
            return;
        }

        //转为命令对象
        Object command = serializer.deserialize(packet.getData(), cmdType.getClazz());
        cmd = CmdEntity.builder().commandType(packet.getCommand())
            .commandClazz(cmdType.getClazz())
            .commandMsg(command)
            .build();
        out.add(cmd);
    }


}
