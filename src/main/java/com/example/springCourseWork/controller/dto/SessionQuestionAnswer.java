package com.example.springCourseWork.controller.dto;

public class SessionQuestionAnswer {

    public String id;

    public boolean isSelected;

    public SessionQuestionAnswer(String id, boolean isSelected) {
        this.id = id;
        this.isSelected = isSelected;
    }

    public SessionQuestionAnswer() {
    }
}
