package com.zk.google.gson;

import com.zk.google.gson.inner.SomeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zk_chs on 4/26/17.
 */
public class Anonymous {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        /**
         * 下面的方法无法进行实例化
         * 因为{@link com.zk.google.gson.inner.SomeClass}的构造函数是protected作用域
         */
        // SomeClass someClass = new SomeClass();

        /**
         * 此时使用花括号进行构造,成为了匿名类
         * SomeClass someClass = new SomeClass<List<String>>(){};
         *
         * 等同下面的类,只不过类名为SomeClass$1这种形式
         * public class SomeClass2<T> extends SomeClass<T> {
         *     SomeClass2 (){
         *         super()
         *     }
         * }
         * 这样一来便能完成构造
         *
         * 重点:
         * 此时我们通过someClass.getClass().getGenericSuperclass()便能获取父类的信息了
         * 如果不采用匿名类,在没有父类的情况下,那么该方法得到的永远是{@link Object}
         *
         * 拿到父类信息之后,接着便能获取父类的泛型信息
         * 强转为{@link ParameterizedType}类型,改接口定义了泛型的获取方式
         * {#getRawType}获取实际类型
         * {#getActualTypeArguments}获取实际泛型类型
         *
         * gson的{@link com.google.gson.reflect.TypeToken}就是这样获取我们给出的泛型信息的
         *
         * 还有另外一种获取泛型的方式,见{@link com.zk.google.gson.inner.SomeClass#main(String[])}
         */
        SomeClass someClass = new SomeClass<List<String>>(){};

        Type superclass = someClass.getClass().getGenericSuperclass();
        log.info("someClass对象地址: {}", someClass); /** 实例对象 */
        log.info("someClass对象类型: {}", someClass.getClass()); /** 对象类型 */
        log.info("someClass对象的父类信息: {}", superclass); /** 父类信息 */

        ParameterizedType parameterized = (ParameterizedType) superclass;
        log.info("someClass对象的父类信息(不包含泛型信息): {}", parameterized.getRawType());
        log.info("someClass对象的父类的泛型: {}", parameterized.getActualTypeArguments()[0]);

        ParameterizedType parameterized2 = (ParameterizedType) parameterized.getActualTypeArguments()[0];
        log.info("someClass对象的父类的泛型的类型: {}", parameterized2.getRawType());
        log.info("someClass对象的父类的泛型的泛型: {}", parameterized2.getActualTypeArguments()[0]);
    }

}
