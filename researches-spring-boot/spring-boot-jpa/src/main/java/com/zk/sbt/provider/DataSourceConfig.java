package com.zk.sbt.provider;

import com.alibaba.druid.pool.DruidDataSource;
import com.zk.sbt.common.MapTool;
import com.zk.sbt.entity.Developer;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * Created by zk_chs on 4/22/17.
 */
@Configuration
//@PropertySource(value = "datasource.properties")
public class DataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "datasource.db1")
    public DataSource db1DataSource() {
        return new DruidDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(db1DataSource())
                .packages(Developer.class)
                .persistenceUnit("db1")
                .properties(MapTool.<String, String>builder()
                        .put(AvailableSettings.HBM2DDL_AUTO, "update")
                        .put(AvailableSettings.SHOW_SQL, "true")
                        .buildMap())
                .build();
    }

}
