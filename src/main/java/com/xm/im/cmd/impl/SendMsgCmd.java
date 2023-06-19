package com.xm.im.cmd.impl;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.Command;
import com.xm.im.cmd.CommandType;
import com.xm.im.cmd.entity.SendMsg;
import io.netty.channel.ChannelHandlerContext;

public class SendMsgCmd implements Command<SendMsg> {

    @Override
    public Boolean support(CmdEntity msg) {
        if (msg != null && CommandType.SendMsg.equals(msg.getCommandType())) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, SendMsg msg) {

    }
}
