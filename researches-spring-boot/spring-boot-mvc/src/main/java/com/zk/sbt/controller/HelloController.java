package com.zk.sbt.controller;

import com.zk.sbt.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Qcon on 2017/4/9.
 */
@RestController
public class HelloController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping(value = "/hello")
    public String hello (@RequestParam("param") int param){
        simpleService.test(param);
        simpleService.core(param);
        simpleService.work(param);
        return "hello!";
    }

}
