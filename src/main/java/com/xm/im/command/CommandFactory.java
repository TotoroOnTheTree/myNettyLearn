package com.xm.im.command;

import com.xm.im.command.dto.SendMsgDto;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xm
 * @Description todo
 * @date 2023/4/21
 * @since 1.0
 **/
public class CommandFactory {

    private static Map<Integer,Class> commandMap = new ConcurrentHashMap<>();

    static {
        commandMap.put(CommandType.SendMsg, SendMsgDto.class);
    }

    public static Class getCommand(int commandTag) {
        return commandMap.get(commandTag);
    }
}
