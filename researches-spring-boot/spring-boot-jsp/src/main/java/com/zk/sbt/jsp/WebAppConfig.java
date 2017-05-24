package com.zk.sbt.jsp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by zk_chs on 4/21/17.
 */
@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter{

    /**
     * 当使用了@EnableWebMvc注解后,WebMvcAutoConfiguration的配置会失效
     * 所以需要在这里配置,最好还是在properties中进行配置,不使用@EnableWebMvc注解
     */
    @Bean
    public ViewResolver viewResolver (){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /** 静态资源文件路径 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/view/**").addResourceLocations("/view/");
    }

    /**
     *  使用enable时,不会拦截.xxx形式的请求
     *  相当于DispatcherServlet的urlPattern为"/",而不是"/*"
     *  "/"不会拦截有后缀的请求,而"/*"会使DispatcherServlet拦截所有请求
     *  比如http://localhost:8080/xxx.html和http://localhost:8080/views/xxx.html便不会被拦截变成404
     */
    @Override
    public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

}
