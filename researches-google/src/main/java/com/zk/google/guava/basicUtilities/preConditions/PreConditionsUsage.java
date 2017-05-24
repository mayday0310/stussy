package com.zk.google.guava.basicUtilities.preConditions;

import static com.google.common.base.Preconditions.*;

/**
 * Created by zk_chs on 4/27/17.
 * 感觉会用到的就是下面这几个了
 */
public class PreConditionsUsage {

    public static void main(String[] args) {
        checkArgument(getTrue());
        checkArgument(getFalse(), "return value is false");

        checkNotNull(getTrue());

        checkState(getTrue());
    }

    private static boolean getTrue (){
        return true;
    }

    private static boolean getFalse (){
        return false;
    }

}
