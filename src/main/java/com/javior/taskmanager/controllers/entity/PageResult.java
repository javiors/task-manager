package com.javior.taskmanager.controllers.entity;

import java.util.List;

public class PageResult<T> {
    List<T> result;
    int totalPages;
    long count;
    int page;

    public PageResult(List<T> result, int totalPages, int page, long count) {
        this.result = result;
        this.totalPages = totalPages;
        this.page = page;
        this.count = count;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
