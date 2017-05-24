package com.zk.google.guava.eventBus;

import com.google.common.eventbus.Subscribe;
import com.zk.google.gson.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Qcon on 2017/5/9.
 */
public class EventBusChangeRecorder {

    private static final Logger log = LogManager.getLogger();

    @Subscribe
    public void recordCustomerChange(Result<String> result) {
        log.info("receive result object -> {}", result);
    }

}
