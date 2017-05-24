package com.zk.google.guava.basicUtilities.object;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.zk.google.gson.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 4/29/17.
 */
public class GuavaObjectsUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        Result<String> r1 = new Result(1, "1", "1");
        Result<String> r2 = new Result(1, "1", "1");

        log.info(Objects.equal(r1, r2));
        log.info(Objects.hashCode(r1));

        log.info(MoreObjects.toStringHelper(r1.getClass().getSimpleName())
                .add("code", r1.getCode())
                .add("message", r1.getMessage())
                .add("data", r1.getData())
        );
    }

}
