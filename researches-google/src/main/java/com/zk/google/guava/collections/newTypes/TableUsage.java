package com.zk.google.guava.collections.newTypes;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 4/30/17.
 *
 * 这个Table和Map<R, Map<C, V>>差不多
 */
public class TableUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        Table<Integer, String, String> records = HashBasedTable.create();
        records.put(1, "Schmo", "recordA");
        records.put(1, "Doe", "recordB");
        records.put(2, "Doe", "recordC");

        log.info(records.row(1)); // returns a Map mapping "Schmo" to "recordA", "Doe" to "recordB"
        log.info(records.column("Doe")); // returns a Map mapping 1 to "recordB", 2 to "recordC"
        log.info(records.rowMap());
        log.info(records.columnMap());
        log.info(records.cellSet());
    }

}
