package com.zk.google.guava.collections.collectionUtilities;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zk_chs on 5/1/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/CollectionUtilitiesExplained
 */
public class CollectionsUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        ListsUsage();
        SetsUsage();
        MapsUsage();
    }

    private static void ListsUsage (){
        List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
        List<Integer> countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
        List<List<Integer>> parts = Lists.partition(countUp, 2); // {{1, 2}, {3, 4}, {5}}
    }

    private static void SetsUsage (){
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");
        /** contains "two", "three", "seven" */
        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
        /** I can use intersection as a Set directly, but copying it can be more efficient if I use it a lot. */
        Set<String> immutableSet = intersection.immutableCopy();

        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");
        /** {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"}, {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}} */
        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
        /** {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}} */
        Set<Set<String>> animalSets = Sets.powerSet(animals);
    }

    private static void MapsUsage (){
        List<String> strings = Lists.newArrayList("one", "three");
        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(strings, String::length);
        log.info("stringsByIndex -> {}", stringsByIndex);

        /** Maps.difference(Map, Map) allows you to compare all the differences between two maps. */
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesDiffering(); // {"c" => (3, 4)}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}
    }

}
