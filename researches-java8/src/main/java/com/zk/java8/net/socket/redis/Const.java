package com.zk.java8.net.socket.redis;

/**
 * Created by zk_chs on 16/6/29.
 */
public class Const {

    public static final byte ASTERISK_BYTE = '*';
    public static final byte DOOLAR_BYTE = '$';
    public static final byte PLUS_BYTE = '+';
    public static final byte MINUS_BYTE = '-';
    public static final byte COLON_BYTE = ':';

    enum COMMAND {
        SET, GET, KEYS
    }

}
