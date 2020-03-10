package com.example.springCourseWork.entity;

import javax.persistence.Entity;

@Entity
public class Session extends BaseEntity {
    private String name;
    private Double result;
    private Long insertDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
    }
}
