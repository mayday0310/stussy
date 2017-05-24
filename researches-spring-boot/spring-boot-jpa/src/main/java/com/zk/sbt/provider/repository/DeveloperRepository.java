package com.zk.sbt.provider.repository;

import com.zk.sbt.entity.Developer;
import com.zk.sbt.entity.QDeveloper;
import com.zk.sbt.provider.repository.support.DB1QuerySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zk_chs on 17/1/13.
 */
@Repository
@Transactional
public class DeveloperRepository extends DB1QuerySupport {

    public Developer findById (Long developerId){
        QDeveloper qDeveloper = QDeveloper.developer;
        Developer developer = queryFactory().selectFrom(qDeveloper)
                .where(qDeveloper.id.eq(developerId))
                .fetchOne();
        return developer;
    }

}
