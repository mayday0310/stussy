package com.zk.google.guava.collections.extensionUtility;

import com.google.common.collect.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zk_chs on 5/2/17.
 */
public class IteratorUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        PeekingIteratorUsage(Lists.newArrayList("String", "String", "java", "guava"));
    }

    private static <E> void PeekingIteratorUsage(List<E> source){
        List<E> result = Lists.newArrayList();
        PeekingIterator<E> iter = Iterators.peekingIterator(source.iterator());
        while (iter.hasNext()) {
            E current = iter.next();
            while (iter.hasNext() && iter.peek().equals(current)) {
                // skip this duplicate element
                iter.next();
            }
            result.add(current);
        }
        log.info("peekingResult -> {}", result);
    }

    public static Iterator<String> AbstractIteratorUsage(final Iterator<String> in) {
        return new AbstractIterator<String>() {
            protected String computeNext() {
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        // skip null values
                        return s;
                    }
                }
                return endOfData();
            }
        };
    }

    private static void AbstractSequentialIterator (){
        Iterator<Integer> powersOfTwo = new AbstractSequentialIterator<Integer>(1) { // note the initial value!
            protected Integer computeNext(Integer previous) {
                // you must additionally pass an initial value, or null if the iterator should end immediately.
                return (previous == 1 << 30) ? null : previous * 2;
            }
        };
    }

}
