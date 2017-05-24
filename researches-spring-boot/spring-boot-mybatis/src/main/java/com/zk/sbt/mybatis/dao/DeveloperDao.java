package com.zk.sbt.mybatis.dao;

import com.zk.sbt.entity.Developer;
import com.zk.sbt.mybatis.dao.mapper.DeveloperMapper;
import com.zk.sbt.mybatis.dataSource.DataSource;
import com.zk.sbt.mybatis.dataSource.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by zk_chs on 4/22/17.
 */
@Repository
public class DeveloperDao {

    @Autowired
    private DeveloperMapper developerMapper;

    @DataSource(DataSourceType.read)
    public Developer findById (Long developerId){
        return developerMapper.findById(developerId);
    }

    @DataSource(DataSourceType.write)
    public Developer findByName (String developerName){
        return developerMapper.findByName(developerName);
    }

}
