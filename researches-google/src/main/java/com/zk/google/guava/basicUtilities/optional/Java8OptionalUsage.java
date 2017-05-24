package com.zk.google.guava.basicUtilities.optional;


import com.zk.google.gson.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.zk.google.gson.Result.*;


/**
 * Created by zk_chs on 4/27/17.
 * 参考 http://www.oschina.net/news/76993/java8-optional
 */
public class Java8OptionalUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
//        Optional<Result<String>> result = getOptionalResult(null);
        Optional<Result<String>> result = getOptionalResult("result data");

        /** 存在即返回, 无则提供默认值 */
        result.orElse(DEFAULT_RESULT);
        /** 存在即返回, 无则由函数来产生 */
        result.orElseGet(() -> DEFAULT_RESULT);
        /** 存在即返回, 无则抛出异常 */
        result.orElseThrow(() -> new NullPointerException("result is null"));

        /** 存在才对它做点什么 */
        result.ifPresent(log::info);

        /**
         * map函数
         * 当result.getMessage存在即返回,无则提供默认值
         * 不会改变result对象内message的值
         */
        result.map(Result::getMessage).orElse("result message is null");

    }

    private static <T> Optional<Result<T>> getOptionalResult (T data){
        return Optional.ofNullable(createResult(data));
    }

    private static <T> Result<T> createResult (T data){
        Result<T> result = new Result<>();
        if (data == null){
            return null;
        }
        result.setCode(100);
        result.setMessage("this is a message");
        result.setData(data);
        return result;
    }

}
