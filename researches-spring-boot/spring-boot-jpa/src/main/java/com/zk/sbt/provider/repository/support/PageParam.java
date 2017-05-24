package com.zk.sbt.provider.repository.support;

public class PageParam {

    private int current;

    private int limit;

    public PageParam(){
        super();
    }

    public PageParam(int current, int limit) {
        this.current = current;
        this.limit = limit;
    }

    public int getCurrent() {
        if (current < 0) {
            current = 0;
        }
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        if (limit <= 0) {
            limit = 10;
        }
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return String.format("current %s, limit %s", getCurrent(), getLimit());
    }
}
