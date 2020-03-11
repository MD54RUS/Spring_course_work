package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.QuestionsItemDTO;

import java.util.List;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);

    QuestionsItemDTO editQuestion(QuestionsItemDTO dto);

    List<QuestionsItemDTO> getQuestionsNew();
}
