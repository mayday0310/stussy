package com.zk.google.guava.basicUtilities.string;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Created by zk_chs on 5/5/17.
 *
 * 详细文档地址 -> https://github.com/google/guava/wiki/StringsExplained
 */
public class StringsUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        stringsJoinerUsage();
        stringsSplitterUsage();
        caseFormatUsage();
    }

    private static void stringsJoinerUsage (){
        Joiner joiner1 = Joiner.on("; ").skipNulls();
        Joiner joiner2 = Joiner.on("; ").useForNull("null");
        Joiner joiner3 = Joiner.on(",");
        log.info(joiner1.join("Harry", null, "Ron", "Hermione"));
        log.info(joiner2.join("Harry", null, "Ron", "Hermione"));
        log.info(joiner3.join(Arrays.asList(3, 5, 7)));
    }

    private static void stringsSplitterUsage (){
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux")
                .forEach(log::info);
    }

    /**
     * jdk not has charset constants, use guava to get Charset
     * @see com.google.common.base.Charsets
     * @see java.nio.charset.Charset
     */
    private static void charsetsUsage (){
        String string = "some string";
        byte[] bytes = string.getBytes(Charsets.UTF_8);
    }

    /**
     * @see CaseFormat#LOWER_CAMEL          lowerCamel
     * @see CaseFormat#LOWER_HYPHEN         lower-hyphen
     * @see CaseFormat#LOWER_UNDERSCORE     lower_underscore
     * @see CaseFormat#UPPER_CAMEL          UpperCamel
     * @see CaseFormat#UPPER_UNDERSCORE     UPPER_UNDERSCORE
     */
    private static void caseFormatUsage (){
        // returns "constantName"
        String format = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        log.info(format);
    }

}
