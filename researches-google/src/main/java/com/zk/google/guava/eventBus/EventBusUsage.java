package com.zk.google.guava.eventBus;

import com.google.common.eventbus.EventBus;
import com.zk.google.gson.Result;

/**
 * Created by Qcon on 2017/5/9.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/EventBusExplained
 */
public class EventBusUsage {

    private static final EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        eventBus.register(new EventBusChangeRecorder());
        eventBus.post(Result.DEFAULT_RESULT);
    }

}
