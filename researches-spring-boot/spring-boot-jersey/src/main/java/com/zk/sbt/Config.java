package com.zk.sbt;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zk.sbt.resource.apiv1.ProjectResourceConfigV1;
import com.zk.sbt.resource.apiv2.ProjectResourceConfigV2;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.DispatcherType;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;

/**
 * Created by zk_chs on 17/1/13.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zk")
@EnableAspectJAutoProxy
@EnableScheduling
@EnableAsync
public class Config {

    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CharacterEncodingFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        registrationBean.setOrder(30);
        registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registrationBean.setInitParameters(new HashMap<String, String>() {{
            put("encoding", "UTF-8");
        }});
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean introspectorCleanupListener() {
        ServletListenerRegistrationBean<IntrospectorCleanupListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new IntrospectorCleanupListener());
        servletListenerRegistrationBean.setOrder(99);
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean requestContextListener() {
        ServletListenerRegistrationBean<RequestContextListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(new RequestContextListener());
        return listenerRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean projectResourceConfigV1() {
        ServletRegistrationBean projectResourceConfigV1
                = new ServletRegistrationBean(new ServletContainer(new ProjectResourceConfigV1()));
        projectResourceConfigV1.addUrlMappings("/apiv1/*");
        projectResourceConfigV1.setName("projectResourceConfigV1");
        projectResourceConfigV1.setLoadOnStartup(1);
        return projectResourceConfigV1;
    }

    @Bean
    public ServletRegistrationBean projectResourceConfigV2() {
        ServletRegistrationBean projectResourceConfigV2
                = new ServletRegistrationBean(new ServletContainer(new ProjectResourceConfigV2()));
        projectResourceConfigV2.addUrlMappings("/apiv2/*");
        projectResourceConfigV2.setName("projectResourceConfigV2");
        projectResourceConfigV2.setLoadOnStartup(1);
        return projectResourceConfigV2;
    }

    /** druid自带监控 */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
//        reg.addInitParameter("allow", "127.0.0.1"); //白名单
//        reg.addInitParameter("deny",""); //黑名单
        reg.addInitParameter("loginUsername", "zk_chs");
        reg.addInitParameter("loginPassword", "zk_chs");
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
