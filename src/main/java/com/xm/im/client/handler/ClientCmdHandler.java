package com.xm.im.client.handler;

import com.xm.im.cmd.CmdEntity;
import com.xm.im.cmd.Command;
import com.xm.im.cmd.impl.HeartBeatCmd;
import com.xm.im.cmd.impl.HeartBeatRespCmd;
import com.xm.im.cmd.impl.SendMsgCmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientCmdHandler extends SimpleChannelInboundHandler<CmdEntity> {
    private static List<Command> commandList = new CopyOnWriteArrayList<>();

    static {
        initCommand();
    }
    private static void initCommand(){
        commandList.add(new SendMsgCmd());
        commandList.add(new HeartBeatRespCmd());
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CmdEntity cmd) throws Exception {
        if (cmd == null) {
            return;
        }
        for (Command command : commandList) {
            if (command.support(cmd)) {
                command.handle(ctx, cmd.getCommandMsg());
                break;
            }
        }
    }
}
