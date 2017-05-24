package com.zk.google.guava.collections.extensionUtility;

import com.google.common.collect.ForwardingList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * Created by zk_chs on 5/2/17.
 */
public class ForwardingUsage<E> extends ForwardingList<E> {

    private static final Logger log = LogManager.getLogger();

    public ForwardingUsage (List<E> delegate){
        this.delegate = delegate;
    }

    private final List<E> delegate; // backing list

    @Override
    protected List<E> delegate() {
        return delegate;
    }

    @Override
    public void add(int index, E element) {
        log.info("add index is {}, element is {}", index, element);
        super.add(index, element);
    }

    @Override
    public boolean add(E element) {
        return standardAdd(element); // implements in terms of add(int, E)
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return standardAddAll(c); // implements in terms of add
    }

}
