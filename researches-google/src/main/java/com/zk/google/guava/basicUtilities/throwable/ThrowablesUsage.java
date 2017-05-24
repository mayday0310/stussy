package com.zk.google.guava.basicUtilities.throwable;

import com.google.common.base.Throwables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 4/29/17.
 * 这里的用法和spring-boot-common里的ExceptionTool相同
 */
public class ThrowablesUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            String[] strings = new String[4];
            String string = strings[6];
            log.info(string);
        } catch (Throwable throwable){
            String exceptionString = Throwables.getStackTraceAsString(throwable);
            log.error(exceptionString);
        }
    }

}
