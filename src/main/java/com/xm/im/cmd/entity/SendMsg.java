package com.xm.im.cmd.entity;

import com.xm.im.cmd.CmdTypeEnum;
import lombok.Data;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
@Data
public class SendMsg implements CmdPacket {

    private String msg;

    @Override
    public int getCommandType() {
        return CmdTypeEnum.SendMsg.getType();
    }
}
