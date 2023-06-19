package com.xm.im.cmd.impl;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.CmdTypeEnum;
import com.xm.im.cmd.Command;
import com.xm.im.cmd.entity.HeartBeat;
import com.xm.im.cmd.entity.HeartBeatResp;
import io.netty.channel.ChannelHandlerContext;

public class StringCmd implements Command<String> {

    @Override
    public Boolean support(CmdEntity msg) {
        if (msg != null && CmdTypeEnum.STRING.getType().equals(msg.getCommandType())) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, String msg) {
        System.out.println("服务端收到消息："+msg);
    }
}
