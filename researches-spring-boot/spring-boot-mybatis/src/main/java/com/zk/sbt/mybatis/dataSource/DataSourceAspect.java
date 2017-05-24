package com.zk.sbt.mybatis.dataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by qcon on 2017/3/29.
 */
@Component
@Aspect
public class DataSourceAspect {

    private static final Logger log = LogManager.getLogger();

    @Before("execution(* com.zk.sbt.mybatis.dao..*Dao.*(..))")
    public void setReadDataSourceType(JoinPoint joinPoint) {
        Class<?> target = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DataSource dataSource = null;

        // 从类初始化
        dataSource = this.getDataSource(target, method);

        // 从接口初始化
        if (dataSource == null) {
            for (Class<?> clazz : target.getInterfaces()) {
                dataSource = getDataSource(clazz, method);
                // 从某个接口中一旦发现注解，不再循环
                if (dataSource != null) {
                    break;
                }
            }
        }

        if (dataSource != null) {
            DataSourceContextHolder.setDataSourceType(dataSource.value());
        }
    }

    private DataSource getDataSource(Class<?> target, Method method) {
        try {
            // 1.优先方法注解
            Class<?>[] types = method.getParameterTypes();
            Method m = target.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                return m.getAnnotation(DataSource.class);
            }

            // 2.其次类注解
            if (target.isAnnotationPresent(DataSource.class)) {
                return target.getAnnotation(DataSource.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
