package com.example.springCourseWork.controller.dto;

public class JournalRequestDTO {
    public String search;
    public int page;
    public int pageSize;

    //todo здесь должен быть фильтр

    public JournalRequestDTO() {
    }

    public JournalRequestDTO(String search, int page, int pageSize) {
        this.search = search;
        this.page = page;
        this.pageSize = pageSize;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
