package com.xm.im.cmd;

import io.netty.channel.ChannelHandlerContext;

public interface Command<T> {

    /**
     * 是否支持该消息
     * @param msg 消息体，具体关注{@link CmdEntity}的实现类
     * @return  true-支持，false-不支持
     */
    Boolean support(CmdEntity msg);

    /**
     * 处理消息
     * @param ctx 上下文
     * @param msg 消息体，具体关注{@link CmdEntity}的实现类
     */
    void handle(ChannelHandlerContext ctx,T msg);
}
