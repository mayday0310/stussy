package com.zk.sbt.provider.data.developer;

import com.zk.sbt.entity.Developer;
import com.zk.sbt.provider.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zk_chs on 17/1/13.
 */
@Transactional
@Service
public class DefaultDeveloperProvider implements DeveloperProvider {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public Developer findById(Long developerId) {
        return developerRepository.findById(developerId);
    }

}
