package com.evanjon.studySpring.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageData<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -9086890054848697802L;
    
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private List<T> list;
    
    public PageData(Page<T> result) {
        this.setCurrentPage(result.getNumber());
        this.setTotalItems(result.getTotalElements());
        this.setTotalPages(result.getTotalPages());
        this.setList(result.getContent());
    }
    
    public PageData(int currentPage, long totalItems, int totalPages, List<T> data) {
        this.setCurrentPage(currentPage);
        this.setTotalItems(totalItems);
        this.setTotalPages(totalPages);
        this.setList(data);
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
