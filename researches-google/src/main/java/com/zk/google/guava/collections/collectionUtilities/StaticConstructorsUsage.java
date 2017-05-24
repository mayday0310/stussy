package com.zk.google.guava.collections.collectionUtilities;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zk_chs on 5/1/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/CollectionUtilitiesExplained
 */
public class StaticConstructorsUsage {

    public static void main(String[] args) {
        List<String> stringList = Lists.newArrayList("String1", "String2", "String3");
        Set<String> stringSet = Sets.newHashSet();
        Map<String, String> stringMap = Maps.newLinkedHashMap();

        Multiset<String> stringMultiset = HashMultiset.create();
        Multimap<String, String> stringStringMultimap = HashMultimap.create();
    }

}
