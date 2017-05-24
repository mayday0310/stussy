package com.zk.sbt.starter.log.autoconfigure;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zk_chs on 4/15/17.
 */
public class LogMethodInterceptor implements MethodInterceptor {

    private static final Logger log = LogManager.getLogger();

    private List<String> exclude;

    public LogMethodInterceptor(String[] exclude) {
        if (exclude != null){
            this.exclude = Arrays.asList(exclude);
        }
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        if(exclude.contains(methodName)) {
            return invocation.proceed();
        }
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        log.info("====method({}), cost({}) ", methodName, (end - start));
        return result;
    }

}
