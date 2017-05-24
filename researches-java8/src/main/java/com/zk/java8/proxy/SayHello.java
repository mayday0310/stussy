package com.zk.java8.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by zk_chs on 16/6/28.
 */
public class SayHello {

    public static void main(String[] args) {
        SayHelloService service = (SayHelloService) Proxy.newProxyInstance(SayHelloService.class.getClassLoader(), new Class<?>[]{SayHelloService.class},
                (proxy, method, arguments) -> {
                    System.out.println("before proxy SayHelloService");
                    Object result = method.invoke(new SayHelloServiceImpl(), arguments);
                    System.out.println("after proxy SayHelloService");
                    return result;
                });
        String result = service.say("world");
        System.out.println(result);
    }

}
