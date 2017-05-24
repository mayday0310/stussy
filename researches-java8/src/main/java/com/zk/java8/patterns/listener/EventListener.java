package com.zk.java8.patterns.listener;


import com.zk.java8.patterns.event.Event;

/**
 * Created by zk_chs on 16/7/7
 */
public interface EventListener<T extends Event> {

    void handleEvent(T event);

}
