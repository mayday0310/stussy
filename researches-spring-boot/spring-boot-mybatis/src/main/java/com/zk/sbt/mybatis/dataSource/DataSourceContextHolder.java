package com.zk.sbt.mybatis.dataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by qcon on 2017/3/29.
 */
public class DataSourceContextHolder {

    private static final Logger log = LogManager.getLogger();

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType (DataSourceType dataSourceType){
        log.info("dataSource切换到：" + dataSourceType);
        contextHolder.set(dataSourceType);
    }

    public static DataSourceType getDatabaseType(){
        return contextHolder.get();
    }

}
