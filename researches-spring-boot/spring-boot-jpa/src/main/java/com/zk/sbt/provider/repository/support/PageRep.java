package com.zk.sbt.provider.repository.support;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class PageRep<T> {

    private PageMeta page = new PageMeta();
    private List<T> list = new ArrayList<>();

    public PageMeta getPage() {
        return page;
    }

    public void setPage(PageMeta page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public <R> PageRep<R> change(Function<T, R> function, boolean human) {
        PageRep<R> pageRep = new PageRep<>();
        if (human) {
            page.setCurrent(page.getCurrent() + 1);
        }
        pageRep.setPage(page);
        pageRep.setList(list.stream().map(function::apply).collect(toList()));
        return pageRep;
    }

}
