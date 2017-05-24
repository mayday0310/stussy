package com.zk.google.guava.basicUtilities.ordering;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zk_chs on 4/29/17.
 * 感觉起来也没java8的好用
 */
public class GuavaOrderingUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        useWithGuava();
    }

    private static void useWithGuava (){
        Ordering<String> ordering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        log.info(ordering.compare("int", "Integer"));
        log.info(ordering.compare("int", "int"));
        log.info(ordering.compare("Integer", "int"));
    }

    /** 这里的写法比隔壁的{@link Java8OrderingUsage}好看 */
    private static class Foo implements Comparable<Foo> {
        private String aString;
        private int anInt;

        @Override
        public int compareTo(Foo that) {
            return ComparisonChain.start()
                    .compare(this.aString, that.aString)
                    .compare(this.anInt, that.anInt)
                    .result();
        }

        public String getaString() {
            return aString;
        }

        public void setaString(String aString) {
            this.aString = aString;
        }

        public int getAnInt() {
            return anInt;
        }

        public void setAnInt(int anInt) {
            this.anInt = anInt;
        }
    }

}
