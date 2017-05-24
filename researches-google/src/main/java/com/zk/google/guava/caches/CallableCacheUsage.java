package com.zk.google.guava.caches;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.zk.google.gson.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by zk_chs on 5/4/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/CachesExplained
 */
public class CallableCacheUsage {

    private static final Logger log = LogManager.getLogger();
    private static Cache<String, Result<String>> cache;
    private static final String key = "key";
    private static final Result<String> result = Result.DEFAULT_RESULT;

    public static void main(String[] args) throws InterruptedException {
        useAsMapMethod();
        log.info("after 5s to invoke another method");
        Thread.sleep(5000);
        useCallable();
    }

    private static void createCache (){
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(); // look Ma, no CacheLoader
    }

    private static void useAsMapMethod (){
        createCache();
        Map<String, Result<String>> map = cache.asMap();
        map.putIfAbsent(key, result);

        log.info("useAsMapMethod: add map.putIfAbsent, then cache size is -> {}, result is -> {}",
                cache.size(),
                result);
    }

    // use callable to add entries to cache
    private static void useCallable (){
        createCache();
        try {
            log.info("useCallable: invoke cache.get(key, Callable<V>) first");
            cache.get(key, () -> doThingsTheHardWay(key));
            log.info("useCallable: invoke cache.get(key, Callable<V>) second");
            cache.get(key, () -> doThingsTheHardWay(key)); // the second time will not perform callable.call()
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("useCallable: add cache.get(key, Callable<V>), then cache size is -> {}, result is -> {}",
                cache.size(),
                result);
    }

    private static Result<String> doThingsTheHardWay (String key){
        try {
            log.info("doThingsTheHardWay, key is -> {}", key);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
