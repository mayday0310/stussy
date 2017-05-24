package com.zk.google.guava.collections.newTypes;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zk_chs on 4/30/17.
 * Multiset比较像Map<K, Integer>, 可以用来对key的出现次数进行计数
 *
 * @see Multiset 的实现类很多, 详细文档地址 -> https://github.com/google/guava/wiki/NewCollectionTypesExplained
 * @see com.google.common.collect.HashMultiset
 * @see com.google.common.collect.TreeMultiset
 * @see com.google.common.collect.LinkedHashMultiset
 * @see com.google.common.collect.ConcurrentHashMultiset
 */
public class MultisetUsage {

    private static final Logger log = LogManager.getLogger();
    private static final List<String> words = Lists.newArrayList("java", "java", "hadoop", "spark", "guava");

    public static void main(String[] args) {
        useInJava();
        useInGuava();
    }

    private static void useInJava (){
        Map<String, Integer> counts = new HashMap<>();
        words.forEach(word -> {
            Integer count = counts.get(word);
            count = count == null ? counts.put(word, 1) : counts.put(word, count + 1);
        });
        counts.forEach((k, v) -> log.info("counts in java: {} -> {}", k, v));
    }

    private static void useInGuava (){
        Multiset<String> counts = HashMultiset.create(words);
        counts.forEachEntry((k, v) -> log.info("counts in guava: {} -> {}", k, v));
    }

}
