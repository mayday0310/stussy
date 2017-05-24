package com.zk.sbt.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qcon on 2017/3/28.
 */
@Aspect
@Component
public class GlobeAspect {

    private static final Logger log = LogManager.getLogger();

    private static final String executeTimeFormatter = "{} {} -> {}, execute method [{}.{}{}] cost time {} ms!";

    /**
     *  额外打印请求执行时间，但抛出异常时不会打印log，因为还没运行到日志处
     *  可以通过{@link org.aspectj.lang.annotation.Before}解决这类情况，不过无法输出执行时间
     */
    @Around("execution(* com.zk.sbt.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object globeAspect (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        /** get request method and args */
        Signature signature = proceedingJoinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        Object[] args = proceedingJoinPoint.getArgs();

        /** get http request method, remote address and request uri */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestMethod = request.getMethod();
        String remoteIp = this.getIpAddr(request);
        String requestUri = request.getRequestURI();

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        long costTime = end - start;
        log.info(executeTimeFormatter, requestMethod, remoteIp, requestUri, className, methodName, args, costTime);

        return result;
    }

    /** 获取客户端IP */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
