package com.zk.google.guava.collections.newTypes;

import com.google.common.collect.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 4/30/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/NewCollectionTypesExplained
 * 这次的东西比较像Map<K, Collection<V>>, guava这个类能够让一个key对应对个value
 * 并且能使用asMap转回Map<K, Collection<V>>
 */
public class MultimapUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        Multimap<String, Integer> multimap = initData();
        ArrayListMultimap<String, Integer> arrayListMultimap = ArrayListMultimap.create(multimap);
        HashMultimap<String, Integer> hashMultimap = HashMultimap.create(multimap);

        listMultimap(arrayListMultimap);
        setMultimap(hashMultimap);
    }

    private static void listMultimap (ListMultimap<String, Integer> multimap){
        log.info("in ListMultimap entries -> {}", multimap.entries());
        log.info("in ListMultimap keys -> {}", multimap.keys());
        log.info("in ListMultimap keySet -> {}", multimap.keySet());
        log.info("in ListMultimap values -> {}", multimap.values());

        log.info("in ListMultimap key = zk, values -> {}", multimap.get("zk"));
    }

    private static void setMultimap (SetMultimap<String, Integer> multimap){
        log.info("in SetMultimap entries -> {}", multimap.entries());
        log.info("in SetMultimap keys -> {}", multimap.keys());
        log.info("in SetMultimap keySet -> {}", multimap.keySet());
        log.info("in SetMultimap values -> {}", multimap.values());

        log.info("in SetMultiMap key = China, values -> {}", multimap.get("China"));
    }

    private static Multimap<String, Integer> initData (){
        Multimap<String, Integer> initData = ArrayListMultimap.create();
        initData.put("zk", 1991);
        initData.put("zk", 2099);
        initData.put("China", 1949);
        initData.put("China", 2017);
        initData.put("current", 2017);
        return initData;
    }

}
