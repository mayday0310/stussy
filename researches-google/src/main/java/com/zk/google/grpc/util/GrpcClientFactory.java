package com.zk.google.grpc.util;


import com.zk.google.grpc.GrpcClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.stream.Stream;

/**
 * Created by zk_chs on 16/6/30.
 */
public class GrpcClientFactory extends BasePooledObjectFactory<GrpcClient> {


    @Override
    public GrpcClient create() throws Exception {
        return new GrpcClient("localhost", 38628);
    }

    @Override
    public PooledObject<GrpcClient> wrap(GrpcClient client) {
        return new DefaultPooledObject<>(client);
    }

    public static void main(String[] args) throws Exception {

        /** 连接池的配置 */
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

        /** 下面的配置均为默认配置,默认配置的参数可以在BaseObjectPoolConfig中找到 */
        poolConfig.setMaxTotal(8); // 池中的最大连接数
        poolConfig.setMinIdle(0); // 最少的空闲连接数
        poolConfig.setMaxIdle(8); // 最多的空闲连接数
        poolConfig.setMaxWaitMillis(-1); // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
        poolConfig.setLifo(true); // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
        poolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L); // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟
        poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞,默认为true

        /** 连接池创建 */
        GenericObjectPool<GrpcClient> objectPool = new GenericObjectPool<>(new GrpcClientFactory(), poolConfig);

        int taskCounts = 15;
        Stream.iterate(1, i -> i + 1)
                .limit(taskCounts)
                .forEach(i -> new Thread(makeTask(objectPool, i)).start());

    }

    private static Runnable makeTask(GenericObjectPool<GrpcClient> objectPool, int i){
        return () -> {
            GrpcClient client = null;
            try {
                client = objectPool.borrowObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String req = "world!";
                String resp = client.request(req);
                System.out.println(resp + " for task" + i);
            } finally {
                /** 将连接对象返回给连接池 */
                objectPool.returnObject(client);
            }
        };
    }
}
