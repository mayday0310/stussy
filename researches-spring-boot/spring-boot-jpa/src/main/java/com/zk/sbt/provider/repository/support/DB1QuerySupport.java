package com.zk.sbt.provider.repository.support;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class DB1QuerySupport extends QuerySupport {

    @PersistenceContext(unitName = "db1")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

}