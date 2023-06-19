package com.xm.im.cmd;

import com.xm.im.cmd.entity.HeartBeat;
import com.xm.im.cmd.entity.HeartBeatResp;
import com.xm.im.cmd.entity.SendMsg;
import java.util.Arrays;
import lombok.Getter;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
@Getter
public enum CmdTypeEnum {
    /** 空包 **/
    EMPTY(-1, null),
    /** 文本 **/
    STRING(1,String.class),
    /** 心跳 **/
    HEARTBEAT(10, HeartBeat.class),
    /** 心跳响应 **/
    HEARTBEAT_RESP(12, HeartBeatResp.class),
    /** 发送消息 **/
    SendMsg(13, com.xm.im.cmd.entity.SendMsg.class),
    ;


    private Integer type;
    private Class clazz;

    CmdTypeEnum(Integer type, Class clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public static CmdTypeEnum getCommand(int cmd) {
        return Arrays.stream(CmdTypeEnum.values())
            .filter(e -> e.getType() == cmd)
            .findFirst()
            .get();
    }

    public boolean isStr() {
        return this.type == CmdTypeEnum.STRING.type;
    }
    public boolean isEmpty() {
        return this.type == CmdTypeEnum.EMPTY.type;
    }
}
