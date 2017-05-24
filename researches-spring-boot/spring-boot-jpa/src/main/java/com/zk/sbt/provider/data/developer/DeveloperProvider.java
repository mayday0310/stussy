package com.zk.sbt.provider.data.developer;

import com.zk.sbt.entity.Developer;

/**
 * Created by zk_chs on 17/1/13.
 */
public interface DeveloperProvider {

    Developer findById(Long id);

}
