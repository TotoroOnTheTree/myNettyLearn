package com.xm.im.cmd.impl;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.Command;
import com.xm.im.cmd.CmdTypeEnum;
import com.xm.im.cmd.entity.HeartBeatResp;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatRespCmd implements Command<HeartBeatResp> {

    @Override
    public Boolean support(CmdEntity msg) {
        if (msg != null && CmdTypeEnum.HEARTBEAT_RESP.getType().equals(msg.getCommandType())) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, HeartBeatResp msg) {
        System.out.println("客户端收到心跳响应："+msg.getMsg());
    }
}
