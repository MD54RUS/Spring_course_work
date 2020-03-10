package com.example.springCourseWork.controller.dto;

import com.example.springCourseWork.entity.Session;

public class SessionItemDTO extends JournalItemDTO {
    public Long id;

    public String name;

    public Double result;

    public Long insertDate;

    public SessionItemDTO(Session session) {
        this.id = session.getId();
        this.name = session.getName();
        this.result = session.getResult();
        this.insertDate = session.getInsertDate();
    }

    public SessionItemDTO() {
    }

}
