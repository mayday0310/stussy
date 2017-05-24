package com.zk.java8.annotation;

/**
 * Created by zk_chs on 16/6/28.
 */
public class TestPrivilege {

    @Privilege( { "a" })
    private void a() {
    }

    @Privilege( { "b" })
    private void b() {
    }

    @Privilege( { "c" })
    private void c() {
    }

}
