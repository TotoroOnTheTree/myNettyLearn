package com.xm.im.protol;

import com.xm.im.cmd.CmdTypeEnum;
import com.xm.im.serializer.SerializeFactory;
import com.xm.im.serializer.SerializeTypeEnum;
import com.xm.im.serializer.Serializer;

public class PacketCreator {

    public static Packet create(byte serializeType, int command, byte[] data) {
        return Packet.builder()
            .serializeType(serializeType)
            .command(command)
            .len(data.length)
            .data(data)
            .build();
    }
    public static Packet createByJson(int command, Object obj) {
        if(obj == null) {
            return empty();
        }
        Serializer serializer = SerializeFactory.get(SerializeTypeEnum.JSON.getCode());
        byte[] data = serializer.serialize(obj);
        return Packet.builder()
            .serializeType(SerializeTypeEnum.JSON.getCode())
            .command(command)
            .len(data.length)
            .data(data)
            .build();
    }

    public static Packet empty() {
        return Packet.builder()
            .serializeType(SerializeTypeEnum.UNKNOWN.getCode())
            .command(CmdTypeEnum.EMPTY.getType())
            .len(0)
            .data(new byte[0])
            .build();
    }
}
