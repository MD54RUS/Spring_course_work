package com.example.springCourseWork.controller.dto;

import java.util.List;

public class AnsweredQuestionDTO {

  public AnsweredQuestionDTO(List<SessionQuestionAnswer> answersList, String id) {
    this.answersList = answersList;
    this.id = id;
  }

  public AnsweredQuestionDTO() {
  }

  public List<SessionQuestionAnswer> answersList;

  public String id;
}
