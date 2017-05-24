package com.zk.sbt.service;

import com.zk.sbt.starter.log.annotation.Log;
import org.springframework.stereotype.Service;

/**
 * Created by zk_chs on 4/15/17.
 */
@Service
public class SimpleService {

    @Log
    public void test(int num) {
        System.out.println("----test---- " + num);
    }

    @Log
    public void core(int num) {
        System.out.println("----core---- " + num);
    }

    public void work(int num) {
        System.out.println("----work---- " + num);
    }

}
