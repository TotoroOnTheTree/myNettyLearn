package com.xm.im.protol;

import com.alibaba.fastjson.JSON;
import com.xm.im.cmd.CommandType;
import com.xm.im.cmd.entity.HeartBeat;
import com.xm.im.serializer.SerializeTypeEnum;
import java.nio.charset.StandardCharsets;

public class PacketCreator {

    private static String HEART_BEAT_DATA = JSON.toJSONString(new HeartBeat("xm"));
    /** 心跳包 **/
    public static Packet HEART_BEAT = Packet.builder()
        .serializeType(SerializeTypeEnum.JSON.getCode())
        .command(CommandType.HEARTBEAT)
        .len(HEART_BEAT_DATA.length())
        .data(HEART_BEAT_DATA.getBytes(StandardCharsets.UTF_8))
        .build();

    public static Packet create(byte serializeType, int command, byte[] data) {
        return Packet.builder()
            .serializeType(serializeType)
            .command(command)
            .len(data.length)
            .data(data)
            .build();
    }
}
