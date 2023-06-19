package com.xm.im.cmd;

import com.xm.im.cmd.entity.HeartBeat;
import com.xm.im.cmd.entity.HeartBeatResp;
import com.xm.im.cmd.entity.SendMsg;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public class CommandType {
    /** 心跳 **/
    public static Integer HEARTBEAT = 1;

    /** 心跳响应 **/
    public static Integer HEARTBEAT_RESP = 2;
    /** 发送消息 **/
    public static Integer SendMsg = 3;
    /** 发送消息响应 **/
    public static Integer SendMsgResp = 4;


    private static Map<Integer,Class> commandTypeMap = new ConcurrentHashMap<>();

    static {
        initCommandType();
    }

    private static void initCommandType(){
        commandTypeMap.put(CommandType.SendMsg, com.xm.im.cmd.entity.SendMsg.class);
        commandTypeMap.put(CommandType.HEARTBEAT, HeartBeat.class);
        commandTypeMap.put(CommandType.HEARTBEAT_RESP, HeartBeatResp.class);
    }


    public static Class getCommand(int commandTag) {
        return commandTypeMap.get(commandTag);
    }
}
