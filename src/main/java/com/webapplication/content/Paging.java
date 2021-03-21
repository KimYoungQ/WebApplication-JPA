package com.webapplication.content;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {

    private int min;

    private int max;

    private int pageTotalCount;

    private int currentPage;

    public Paging(int contentTotalCount, int currentPage, int contentCountPerPage, int paginationCount) {

        this.currentPage = currentPage;

        pageTotalCount = contentTotalCount / contentCountPerPage;

        if (contentTotalCount % contentCountPerPage > 0) {
            pageTotalCount++;
        }

        min = (((currentPage - 1) / contentCountPerPage) * contentCountPerPage) + 1;
        max = min + paginationCount - 1;

        if (max > pageTotalCount) {
            max = pageTotalCount;
        }
    }


}
