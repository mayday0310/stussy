package com.zk.sbt.starter.log.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * Created by zk_chs on 4/15/17.
 */
@ConfigurationProperties(prefix = "mylog")
public class LogProperties {

    private String exclude;
    private String[] excludeArr;

    @PostConstruct
    public void init() {
        this.excludeArr = StringUtils.split(exclude, ",");
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String[] getExcludeArr() {
        return excludeArr;
    }

}
