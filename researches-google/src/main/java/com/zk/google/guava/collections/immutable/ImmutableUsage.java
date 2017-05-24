package com.zk.google.guava.collections.immutable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 4/29/17.
 * 文档地址 -> https://github.com/google/guava/wiki/ImmutableCollectionsExplained
 */
public class ImmutableUsage {

    private static final Logger log = LogManager.getLogger();
    private static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "purple");

    public static void main(String[] args) {
        createSet();
        createMap();
    }

    private static void createSet (){
        ImmutableSet<String> immutableSet = ImmutableSet.copyOf(COLOR_NAMES);
        log.info("copyOf方式创建: {}", immutableSet);
        immutableSet = ImmutableSet.of("a", "b", "c");
        log.info("of方式创建: {}", immutableSet);
        immutableSet = ImmutableSet.<String>builder()
                .addAll(immutableSet)
                .add("d")
                .build();
        log.info("builder方式创建: {}", immutableSet);
    }

    private static void createMap (){
        ImmutableMap<Integer, String> immutableMap = ImmutableMap.of(1, "No1", 2, "No2");
        log.info(immutableMap);
    }

}
