package com.zk.google.guava.caches;

import com.google.common.cache.*;
import com.zk.google.gson.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zk_chs on 5/5/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/CachesExplained
 */
public class CacheRemovalListenerUsage {

    private static final Logger log = LogManager.getLogger();
    private static final String key = "key";
    private static LoadingCache<String, Result<String>> caches;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        cacheRemovalListenerUsage();
    }

    // removal listener operations are executed synchronously by default
    // use RemovalListeners.asynchronous(RemovalListener, Executor) to decorate a RemovalListener to operate asynchronously.
    private static void cacheRemovalListenerUsage () throws InterruptedException, ExecutionException {
        CacheLoader<String, Result<String>> loader = new CacheLoader<String, Result<String>>() {
            public Result<String> load(String key) throws Exception {
                return Result.DEFAULT_RESULT;
            }
        };

        RemovalListener<String, Result<String>> removalListener = (removal) -> {
            Result<String> removeResult = removal.getValue();
            log.info("listened the removal event, the removed value is -> {}", removeResult);
        };
//      RemovalListeners.asynchronous(removalListener, executor);

        caches = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .removalListener(removalListener)
                .build(loader);

        caches.get(key);
        log.info("get key from cache: {}", caches.get(key));
        Thread.sleep(5000);
        log.info("get key from cache: {}", caches.get(key));
    }



}
