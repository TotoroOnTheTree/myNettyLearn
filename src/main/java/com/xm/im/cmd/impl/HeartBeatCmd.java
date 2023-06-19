package com.xm.im.cmd.impl;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.Command;
import com.xm.im.cmd.CommandType;
import com.xm.im.cmd.entity.HeartBeat;
import com.xm.im.cmd.entity.HeartBeatResp;
import com.xm.im.cmd.entity.SendMsg;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatCmd implements Command<HeartBeat> {

    @Override
    public Boolean support(CmdEntity msg) {
        if (msg != null && CommandType.HEARTBEAT.equals(msg.getCommandType())) {
            return true;
        }
        return false;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, HeartBeat msg) {
        System.out.println("服务端收到心跳："+msg.getMsg());
        HeartBeatResp resp = new HeartBeatResp();
        resp.setMsg("收到心跳");
        ctx.writeAndFlush(resp);
    }
}
