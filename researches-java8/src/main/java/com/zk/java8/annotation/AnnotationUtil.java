package com.zk.java8.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zk_chs on 16/6/28.
 */
public class AnnotationUtil {

    public static AnnotationUtil anno = null;

    public static AnnotationUtil getInstance() {
        if (anno == null) {
            anno = new AnnotationUtil();
        }
        return anno;
    }

    @SuppressWarnings("all")
    public Map<String, String> loadVlaue(Class<? extends Annotation> annotationClasss,
                                         String annotationField, Class<?> clazz) throws Exception {

        System.out.println("处理Annotation类名称  === " + annotationClasss.getName());
        System.out.println("处理Annotation类属性名称  === " + annotationField);
        System.out.println("处理Annotation的调用类名称  === " + clazz.getName());
        Map<String, String> map = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClasss)) {
                Annotation p = method.getAnnotation(annotationClasss);
                Method m = p.getClass()
                        .getDeclaredMethod(annotationField, null);
                String[] values = (String[]) m.invoke(p, null);
                for (String key : values) {
                    System.out.println("注解值 === " + key);
                    map.put(key, key);
                }
            }
        }
        System.out.println("map数量  === " + map.size());
        return map;
    }

    public static void main(String[] args) throws Exception {

        AnnotationUtil.getInstance().loadVlaue(Privilege.class, "value",
                TestPrivilege.class);
    }

}
