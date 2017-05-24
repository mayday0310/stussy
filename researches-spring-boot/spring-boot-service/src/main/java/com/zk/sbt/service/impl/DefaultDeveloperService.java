package com.zk.sbt.service.impl;

import com.zk.sbt.entity.Developer;
import com.zk.sbt.mybatis.dao.DeveloperDao;
import com.zk.sbt.service.interfaces.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zk_chs on 4/22/17.
 */
@Service
@Transactional
public class DefaultDeveloperService implements DeveloperService {

    @Autowired
    private DeveloperDao developerDao;

    @Override
    public Developer findById(Long developerId) {
        Developer developer = developerDao.findById(developerId);
        return developer;
    }

    @Override
    public Developer findByName(String developerName) {
        Developer developer = developerDao.findByName(developerName);
        return developer;
    }


}
