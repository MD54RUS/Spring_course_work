package com.example.springCourseWork.controller.dto;

import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Question;

import java.util.List;
import java.util.stream.Collectors;




public class QuestionsItemDTO extends JournalItemDTO {
    public String name;
    public List<AnswerItemDTO> answers;

    public QuestionsItemDTO() {
    }

    public QuestionsItemDTO(Question question, List<Answer> answers) {
        this.id = question.getId().toString();
        this.name = question.getName();
        this.answers = answers.stream()
                .map(AnswerItemDTO::new)
                .collect(Collectors.toList());
    }
}
