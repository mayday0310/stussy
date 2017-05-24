package com.zk.google.guava.basicUtilities.ordering;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

/**
 * Created by zk_chs on 4/29/17.
 */
public class Java8OrderingUsage {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        useWithJava8();
    }

    private static void useWithJava8 (){
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        log.info(comparator.compare("int", "Integer"));
        log.info(comparator.compare("int", "int"));
        log.info(comparator.compare("Integer", "int"));
    }

    private static class Foo implements Comparable<Foo> {
        private String aString;
        private Integer anInt;

        @Override
        public int compareTo(Foo that) {
            int cmp = aString.compareTo(that.aString);
            if (cmp != 0) {
                return cmp;
            }
            cmp = anInt.compareTo(that.anInt);
            if (cmp != 0) {
                return cmp;
            }
            return 0;
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
