package com.example.springCourseWork.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Session extends BaseEntity {

  @Column
  private String name;

  @Column
  private Long result;

  @Column
  private Long insertDate;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getResult() {
    return result;
  }

  public void setResult(Long result) {
    this.result = result;
  }

  public Long getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(long insertDate) {
    this.insertDate = insertDate;
  }
}
