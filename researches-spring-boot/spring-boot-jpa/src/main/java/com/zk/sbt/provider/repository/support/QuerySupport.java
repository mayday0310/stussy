package com.zk.sbt.provider.repository.support;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public abstract class QuerySupport {

    public JPADeleteClause delete(EntityPath<?> path) {
        return queryFactory().delete(path);
    }

    protected <T> JPAQuery<T> select(Expression<T> expr) {
        return queryFactory().select(expr);
    }

    protected JPAQuery<Tuple> select(Expression<?>... exprs) {
        return queryFactory().select(exprs);
    }

    protected <T> JPAQuery<T> selectDistinct(Expression<T> expr) {
        return queryFactory().selectDistinct(expr);
    }

    protected JPAQuery<Tuple> selectDistinct(Expression<?>... exprs) {
        return queryFactory().selectDistinct(exprs);
    }

    protected JPAQuery<Integer> selectOne() {
        return queryFactory().selectOne();
    }

    protected JPAQuery<Integer> selectZero() {
        return queryFactory().selectZero();
    }

    protected <T> JPAQuery<T> selectFrom(EntityPath<T> from) {
        return queryFactory().selectFrom(from);
    }

    protected JPAQuery<?> from(EntityPath<?> from) {
        return queryFactory().from(from);
    }

    protected JPAQuery<?> from(EntityPath<?>... from) {
        return queryFactory().from(from);
    }

    protected JPAUpdateClause update(EntityPath<?> path) {
        return queryFactory().update(path);
    }

    protected JPAQuery<?> query() {
        return queryFactory().query();
    }

    protected <T> PageRep<T> findPage(Supplier<JPAQuery<T>> supplier, PageParam pageParam) {
        JPAQuery<T> query = supplier.get();

        int offset = pageParam.getCurrent() * pageParam.getLimit();
        int limit = pageParam.getLimit();

        query.offset(offset);
        query.limit(limit);

        QueryResults<T> results = query.fetchResults();
        long total = results.getTotal();

        List<T> content = total > offset ? results.getResults() : Collections.<T>emptyList();

        int pages = (int) ((total + limit - 1) / limit);
        int current = pageParam.getCurrent() > pages - 1 ? pages - 1 : pageParam.getCurrent();
        PageMeta meta = new PageMeta();
        meta.setTotal(total);
        meta.setCurrent(current);
        meta.setFirstPage(current == 0);
        meta.setLastPage(current == pages - 1);
        meta.setLimit(limit);
        meta.setPages(pages);

        PageRep<T> rep = new PageRep<>();
        rep.setPage(meta);
        rep.setList(content);
        return rep;
    }

    public <T> T save(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public <T> List<T> save(List<T> entities) {
        return entities.stream()
                .map(this::save)
                .collect(toList());
    }

    public <T> T update(T entity) {
        return getEntityManager().merge(entity);
    }

    public <T> List<T> update(List<T> entities){
        return entities.stream().map(this::update).collect(Collectors.toList());
    }

    public void delete(Object entity) {
        getEntityManager().remove(entity);
    }

    public <T> void delete(List<T> entities){
        entities.forEach(this::delete);
    }

    protected JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(getEntityManager());
    }

    public abstract EntityManager getEntityManager();

}
