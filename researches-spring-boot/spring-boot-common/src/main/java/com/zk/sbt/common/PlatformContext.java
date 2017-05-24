package com.zk.sbt.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Qcon on 2017/4/26.
 */
public class PlatformContext {

    public static final String CLIENT_IP = "clientIp";

    private static ThreadLocal<Map<String, Object>> requestContextHolder = ThreadLocal.withInitial(HashMap::new);

    public static Object get(String name) {
        return getMap().get(name);
    }

    public static String getClientIp() {
        return getString(CLIENT_IP);
    }

    public static String getString(String name) {
        return MapTool.getString(getMap(), name);
    }

    public static Integer getInteger(String name) {
        return MapTool.getInteger(getMap(), name);
    }

    public static Long getLong(String name) {
        return MapTool.getLong(getMap(), name);
    }

    public static void add(String name, Object value) {
        getMap().put(name, value);
    }

    public static Map<String, Object> getMap() {
        return requestContextHolder.get();
    }

    public static void clear() {
        requestContextHolder.remove();
    }

}
