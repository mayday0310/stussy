package com.zk.google.guava.collections.newTypes;

import com.google.common.collect.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 4/30/17.
 *
 * 不知道这东西会不会有用到的一天, 看起来还是蛮不错的, 处理范围的好东西
 */
public class RangeSetRangeMapUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
        log.info("rangeSet current is -> {}", rangeSet);
        log.info("rangeSet contains 5 = {}, contains 6 = {} ", rangeSet.contains(5), rangeSet.contains(6));

        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}
        log.info("rangeMap current is -> {}", rangeMap);
        log.info("rangeMap get 4 = {}, get 10 = {}", rangeMap.get(4), rangeMap.get(10));
    }

}
