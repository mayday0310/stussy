package com.zk.java8.patterns.source;


import com.zk.java8.patterns.event.Event;
import com.zk.java8.patterns.listener.EventListener;

/**
 * Created by zk_chs on 16/7/7.
 */
public interface EventSource {

    void addEventListener(EventListener<? extends Event> listener);

    void removeEventListener(EventListener<? extends Event> listener);

    void notifyListeners(Event event);

}
