package com.zk.google.guava.collections.collectionUtilities;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by zk_chs on 5/1/17.
 * <p>
 * 详细文档地址 -> https://github.com/google/guava/wiki/CollectionUtilitiesExplained
 */
public class IterablesUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        /** concatenated the elements */
        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3, 4, 5),
                Ints.asList(5, 6, 7));

        /** Returns the number of occurrences of the object. */
        int count = Iterables.<Integer>frequency(concatenated, 5);
        log.info("count -> {}", count);

        /** Returns an unmodifiable view of the iterable partitioned into chunks of the specified size. */
        Iterable<List<Integer>> partition = Iterables.partition(concatenated, 3);
        log.info("partition -> {}", partition);

        /** Returns the first element of the iterable, or the default value if empty. */
        Integer firstElement = Iterables.getFirst(concatenated, 100);
        log.info("firstElement -> {}", firstElement);

        /** Returns the last element of the iterable, or fails fast with a NoSuchElementException if it's empty. */
        Integer lastElement = Iterables.getLast(concatenated); // get the last element 6
        log.info("lastElement -> {}", lastElement);

        /** Returns true if the iterables have the same elements in the same order. */
        List<Integer> asList = Lists.newArrayList(concatenated);
        boolean equals = Iterables.elementsEqual(concatenated, asList);
        log.info("equals -> {}", equals);

        /** Returns an unmodifiable view of the iterable. */
        Iterable<Integer> concatenated2 = Iterables.unmodifiableIterable(concatenated);
        log.info("unmodifiable iterable -> {}", concatenated2);

        /** Returns an Iterable returning at most the specified number of elements. */
        concatenated2 = Iterables.limit(concatenated, 6);
        log.info("limit iterable -> {}", concatenated2);

        /** Returns the only element in Iterable. Fails fast if the iterable is empty or has multiple elements. */
        Integer theElement = Iterables.getOnlyElement(concatenated);
        log.info("the Element -> {}", theElement);
    }

}
