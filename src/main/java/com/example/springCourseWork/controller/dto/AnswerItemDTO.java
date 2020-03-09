package com.example.springCourseWork.controller.dto;

import com.example.springCourseWork.entity.Answer;

public class  AnswerItemDTO {

    public String id;

    public String answerText;

    public Boolean isCorrect;

    public AnswerItemDTO() {
    }

    public AnswerItemDTO(Answer answer) {
        id = answer.getId().toString();
        answerText = answer.getName();
        isCorrect = answer.getCorrect();
    }
}
