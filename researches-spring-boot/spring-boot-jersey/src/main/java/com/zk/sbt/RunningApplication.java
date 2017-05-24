package com.zk.sbt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by zk_chs on 4/20/17.
 */
@SpringBootApplication
public class RunningApplication {

    /**
     * 关于多个properties,下面的是一种配置方式
     * 也可以在需要的地方使用{@link org.springframework.context.annotation.PropertySource @PropertySource}
     * 比如{@link com.zk.sbt.provider.DataSourceConfig DataSourceConfig}
     */
    public static void main(String[] args) {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        new SpringApplicationBuilder(RunningApplication.class)
                .properties("spring.config.name=application,datasource")
                .run(args);
    }

}
