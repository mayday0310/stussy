package com.zk.google.grpc;


import io.grpc.stub.StreamObserver;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zk_chs on 16/6/30.
 */
public class HelloWorldRpcServiceImpl extends HelloWorldRpcServiceGrpc.HelloWorldRpcServiceImplBase {

    /** Grpc并不是单线程的 */
//    public static int count = 0;

    /** 原子Integer */
    public static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void sayHello(HelloWorldRequest request, StreamObserver<HelloWorldResponse> responseObserver) {
        String req = request.getRequest();
        HelloWorldResponse resp = HelloWorldResponse.newBuilder()
                .setResponse("hello " + req + " ")
//                .setResponse("hello " + req + " " + count.get())
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
        /** 自增操作,如果使用注释掉的形式的话,还是会发生线程交互 */
//        count.incrementAndGet();
//        System.out.println(count.get());
        System.out.println(count.incrementAndGet() + " " + Thread.currentThread().getName());
    }

}
