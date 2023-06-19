package com.xm.im.cmd.impl;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.Command;
import com.xm.im.cmd.CommandType;
import com.xm.im.cmd.entity.HeartBeat;
import com.xm.im.cmd.entity.HeartBeatResp;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatRespCmd implements Command<HeartBeatResp> {

    @Override
    public Boolean support(CmdEntity msg) {
        if (msg != null && CommandType.HEARTBEAT_RESP.equals(msg.getCommandType())) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, HeartBeatResp msg) {
        System.out.println("客户端收到心跳："+msg.getMsg());
    }
}
