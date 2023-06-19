package com.xm.im.server.handler;

import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

public class HeartBeatIdleHandle extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;
    public HeartBeatIdleHandle() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }
}
