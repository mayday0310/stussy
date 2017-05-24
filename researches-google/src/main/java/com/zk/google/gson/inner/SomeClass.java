package com.zk.google.gson.inner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zk_chs on 4/26/17.
 */
public class SomeClass<T> {

    private static final Logger log = LogManager.getLogger();

    protected SomeClass (){

    }

    public static void main(String[] args) {
        SomeClass<List<String>> someClass = new SomeClass<List<String>>(){};
        
        Type superclass = someClass.getClass().getGenericSuperclass();
        ParameterizedType parameterized = (ParameterizedType) superclass;
        log.info("someClass对象的父类信息(不包含泛型信息): {}", parameterized.getRawType());
        log.info("someClass对象的父类的泛型: {}", parameterized.getActualTypeArguments()[0]);

        ParameterizedType parameterized2 = (ParameterizedType) parameterized.getActualTypeArguments()[0];
        log.info("someClass对象的父类的泛型的类型: {}", parameterized2.getRawType());
        log.info("someClass对象的父类的泛型的泛型: {}", parameterized2.getActualTypeArguments()[0]);
    }

}
