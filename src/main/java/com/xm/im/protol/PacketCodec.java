package com.xm.im.protol;

import io.netty.buffer.ByteBuf;

/**
 * @author xm
 * @Description 包编码器
 * @date 2023/4/21
 * @since 1.0
 **/
public class PacketCodec {

    /**
     * 将包按协议格式进行编码
     * @param buf
     * @param packet
     */
    public static void encode(ByteBuf buf,Packet packet){
        buf.writeInt(packet.getMagic());
        buf.writeByte(packet.getVersion());
        buf.writeByte(packet.getSerializeType());
        buf.writeInt(packet.getCommand());
        buf.writeInt(packet.getLen());
        buf.writeBytes(packet.getData());
    }

    /**
     * 将包按协议格式进行解码
     * @param buf
     * @return
     */
    public static Packet decode(ByteBuf buf){
        int magic = buf.readInt();
        byte version = buf.readByte();
        byte serializeType = buf.readByte();
        int command = buf.readInt();
        int length = buf.readInt();
        byte[] data = new byte[length];
        buf.readBytes(data);
        return Packet.builder()
            .version(version)
            .serializeType(serializeType)
            .command(command)
            .len(length)
            .data(data)
            .build();
    }
}
