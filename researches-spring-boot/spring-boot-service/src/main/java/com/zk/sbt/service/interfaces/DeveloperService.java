package com.zk.sbt.service.interfaces;

import com.zk.sbt.entity.Developer;

/**
 * Created by zk_chs on 4/22/17.
 */
public interface DeveloperService {

    Developer findById(Long developerId);

    Developer findByName(String developerName);

}
