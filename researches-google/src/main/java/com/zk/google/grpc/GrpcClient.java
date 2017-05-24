package com.zk.google.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by zk_chs on 16/6/30.
 */
public class GrpcClient {

    private static final Logger log = LogManager.getLogger();

    private final ManagedChannel channel;
    private final HelloWorldRpcServiceGrpc.HelloWorldRpcServiceBlockingStub blockingStub;

    public GrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = HelloWorldRpcServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String request(String req) {
        HelloWorldRequest request = HelloWorldRequest.newBuilder()
                .setRequest(req)
                .build();
        return blockingStub.sayHello(request).getResponse();
    }

    public static void main(String[] args) throws Exception {
        GrpcClient client = new GrpcClient("localhost", 38628);
        String req = "world!";
        String response = client.request(req);
        log.info("response -> ", response);
    }

}
