package com.zk.java8.net.socket.redis;

/**
 * Created by zk_chs on 16/6/29.
 */
public class RedisException extends RuntimeException {

    public RedisException (String message){
        super(message);
    }

    public RedisException (Throwable e){
        super(e);
    }

    public RedisException (String message, Throwable e){
        super(message, e);
    }

}
