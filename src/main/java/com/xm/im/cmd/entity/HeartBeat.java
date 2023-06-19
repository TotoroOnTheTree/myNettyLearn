package com.xm.im.cmd.entity;

import com.xm.im.cmd.CmdTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeartBeat implements CmdPacket {

    private String msg;

    @Override
    public int getCommandType() {
        return CmdTypeEnum.HEARTBEAT.getType();
    }
}
