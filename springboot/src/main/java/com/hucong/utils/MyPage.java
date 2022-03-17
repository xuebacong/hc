package com.hucong.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class MyPage<T> {
    private int totalRows;
    private int current;
    private int totalPages;
    private int pageSize;
    private List<T> rows;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;

    public MyPage(int totalRows, int current, int totalPages, int pageSize, List<T> rows) {
        this.totalRows = totalRows;
        this.current = current;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.rows = rows;
    }
    public void setAttribute(){
        this.isFirstPage = current == 1;
        this.isLastPage = current == totalPages || totalPages == 0;
        this.hasPreviousPage = current > 1;
        this.hasNextPage = current < totalPages;
    }
}
