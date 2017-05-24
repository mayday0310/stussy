package com.zk.google.guava.caches;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.zk.google.gson.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by zk_chs on 5/5/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/CachesExplained
 */
public class CacheEvictionUsage {

    private static final Logger log = LogManager.getLogger();
    private static final String key = "key";
    private static LoadingCache<String, Result<String>> caches;

    public static void main(String[] args) {
        sizeBasedEviction();
    }

    private static void createWeightCache() {
        caches = CacheBuilder.newBuilder()
                .maximumWeight(10)
                .weigher((Weigher<String, Result<String>>) (k, v) -> 1)
                .build(new CacheLoader<String, Result<String>>() {
                    @Override
                    public Result<String> load(String key) { // no checked exception
                        log.info("cache the key -> {}", key);
                        return Result.DEFAULT_RESULT;
                    }
                });
    }

    // when the count weight grow to customer maximumWeight,
    // will evict the entry which load at first
    private static void sizeBasedEviction() {
        createWeightCache();
        Stream.iterate(1, item -> item + 1)
                .limit(20)
                .forEach(item -> {
                    try {
                        caches.get(key + item);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
        log.info("now caches size is {}, and the keys -> {}", caches.size(), caches.asMap().keySet());
    }

    // this is easy to understand, but very useful
    private static void timeBasedEviction() {
        CacheBuilder.newBuilder()
                .expireAfterAccess(1000, TimeUnit.SECONDS)
                .build();

        CacheBuilder.newBuilder()
                .expireAfterWrite(1000, TimeUnit.SECONDS)
                .build();
    }

    // this is hard to describe, associate to garbage collection
    // CacheBuilder.weakKeys()
    // CacheBuilder.weakValues()
    // CacheBuilder.softValues()
    private static void referenceBasedEviction() {}

}
