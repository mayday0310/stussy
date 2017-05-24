package com.zk.google.guava.collections.newTypes;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by zk_chs on 4/30/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/NewCollectionTypesExplained
 * BiMap可以让我们通过Value来索引Key, 不过需要我们的value也为unique
 * 如果put(key, value)时value已经存在, 会抛出IllegalArgumentException
 * 可以使用forcePut(key, value)来删除已存在的entry
 */
public class BiMapUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        Map<String, Integer> initData = initData();

        usage(initData);
    }

    private static void usage (Map<String, Integer> initData){
        BiMap<String, Integer> keyToValue = HashBiMap.create(initData);
        BiMap<Integer, String> valueToKey = keyToValue.inverse();

        log.info("key maps value -> {}", keyToValue);
        log.info("value maps key -> {}", valueToKey);
    }

    private static Map<String, Integer> initData (){
        Map<String, Integer> initData = Maps.newHashMap();
        initData.put("zk", 1991);
        initData.put("now", 2017);
        initData.put("next", 2018);
        return initData;
    }

}
