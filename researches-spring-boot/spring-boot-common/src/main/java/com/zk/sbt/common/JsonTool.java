package com.zk.sbt.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by zk_chs on 16/12/14.
 */
public class JsonTool {

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
        gson = gsonBuilder.create();
    }

    private static Gson gson;

    public static String toJson (Object object){
        return gson.toJson(object);
    }

    public static <K, V> Map<K, V> toMap (String json){
        return gson.fromJson(json, Map.class);
    }

    public static <T> T toObject (String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

    public static <T> List<T> toList (String json){
        List<T> rep = gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
        return rep;
    }

}
