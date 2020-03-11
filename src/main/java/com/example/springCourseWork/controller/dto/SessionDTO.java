package com.example.springCourseWork.controller.dto;

import java.util.List;

public class SessionDTO {
    public String name;
    public List<AnsweredQuestionDTO> questionsList;

    public SessionDTO(String name, List<AnsweredQuestionDTO> questionsList) {
        this.name = name;
        this.questionsList = questionsList;
    }

    public SessionDTO() {
    }
}
