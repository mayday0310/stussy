package com.zk.java8.patterns.test;


import com.zk.java8.patterns.event.ClickEvent;
import com.zk.java8.patterns.event.DblClickEvent;
import com.zk.java8.patterns.event.Event;
import com.zk.java8.patterns.listener.ClickEventListener;
import com.zk.java8.patterns.listener.DblClickEventListener;
import com.zk.java8.patterns.source.ClickSource;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zk_chs on 16/7/7.
 */
public class Client {

    private Event currentEvent;

    private ClickSource clickSource;

    @Before
    public void init (){
        clickSource = new ClickSource();
        clickSource.addEventListener((ClickEventListener) event -> System.out.println("click!"));
        clickSource.addEventListener((DblClickEventListener) event -> System.out.println("double Click!"));
    }

    @Test
    public void testClick (){
        currentEvent = new ClickEvent();
        clickSource.notifyListeners(currentEvent);
        currentEvent = new DblClickEvent();
        clickSource.notifyListeners(currentEvent);
    }

}
