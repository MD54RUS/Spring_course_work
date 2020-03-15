package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);

    QuestionsItemDTO editQuestion(QuestionsItemDTO dto);

    List<QuestionsItemDTO> getQuestionsNew();

    Page<Question> findAtPage(int pageIndex, int rowCount, Sort.Direction direction, String field);
}
