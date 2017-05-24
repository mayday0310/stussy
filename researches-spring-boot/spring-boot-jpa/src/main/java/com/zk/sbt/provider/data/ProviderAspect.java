package com.zk.sbt.provider.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zk_chs on 16/12/30.
 */
@Aspect
@Component
@Order(100)
public class ProviderAspect {

    private static Logger log = LogManager.getLogger();

    @Around("execution(* com.zk.sbt.provider.data..*(..))")
    public Object beforeDataMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        long start = System.currentTimeMillis();
        try {
            Object object = pjp.proceed();
            long end = System.currentTimeMillis();
            long runtime = end - start;
            log.debug("execute method = {}, execute time = {}", methodName, runtime);
            return object;
        } catch (Throwable throwable) {
            long end = System.currentTimeMillis();
            long runtime = end - start;
            log.debug("execute method = {}, execute time = {}", methodName, runtime);
            throw throwable;
        }
    }

}
