package com.zk.java8.callback;

/**
 * Created by zk_chs on 16/7/29.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        new TestClassBack().compute(10, () -> System.out.println("this is callback!"));
    }

}

class TestClassBack {

    void compute(int n, ComputeCallBack callBack) throws InterruptedException {
        System.out.println("this is " + n + ", and callback will run after 1s...");
        Thread.sleep(1000);
        callBack.onCompleteEnd();
    }

}

interface ComputeCallBack {
    void onCompleteEnd();
}
