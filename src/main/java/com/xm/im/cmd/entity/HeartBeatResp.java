package com.xm.im.cmd.entity;

import com.xm.im.cmd.CmdTypeEnum;
import lombok.Data;

@Data
public class HeartBeatResp implements CmdPacket {

    private String msg;

    @Override
    public int getCommandType() {
        return CmdTypeEnum.HEARTBEAT_RESP.getType();
    }
}
