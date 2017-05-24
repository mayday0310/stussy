package com.zk.google.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by qcon on 2017/3/21.
 */
public class Gson4Test {

    private static final Logger log = LogManager.getLogger();

    private static Gson gson = new Gson();

    public static void main(String[] args) {
        serializedNameTest();
        generic4Gson();
    }

    /** Gson中使用别名 */
    private static void serializedNameTest (){
        String json = "{\"name\":\"zk_chs\",\"age\":24,\"emailAddress\":\"zk_chs@example.com\",\"email\":\"zk_chs2@example.com\",\"email_address\":\"zk_chs3@example.com\"}";
        GsonUser gsonUser = gson.fromJson(json, GsonUser.class);
        log.info(gsonUser.getEmailAddress()); // zk_chs3@example.com
    }

    /** Gson中使用泛型 */
    private static void generic4Gson (){
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String[] strings = gson.fromJson(jsonArray, String[].class);
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
    }

    /** Gson泛型封装 */
    private static <T> Result<T> genericPacket4Gson (){
        String json = "{......}";
        fromJsonObject(json, GsonUser.class);
        fromJsonArray(json, GsonUser.class);
        return null;
    }

    /** 解析Result中data是Object类型的情况 */
    private static <T> Result<T> fromJsonObject (String json, Class<T> clazz){
        Type type = new ParameterizedTypeImpl(Result.class, new Class[]{clazz});
        return gson.fromJson(json, type);
    }

    /** 解析Result中data是array的情况 */
    private static <T> Result<List<T>> fromJsonArray (String json, Class<T> clazz){
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(Result.class, new Type[]{listType});
        return gson.fromJson(json, type);
    }

}
