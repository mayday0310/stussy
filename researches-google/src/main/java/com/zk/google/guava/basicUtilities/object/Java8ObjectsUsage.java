package com.zk.google.guava.basicUtilities.object;

import com.zk.google.gson.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Created by zk_chs on 4/29/17.
 */
public class Java8ObjectsUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        Result<String> r1 = new Result(1, "1", "1");
        Result<String> r2 = new Result(1, "1", "1");

        log.info(Objects.equals(r1, r2));
        log.info(Objects.hash(r1));
    }

}