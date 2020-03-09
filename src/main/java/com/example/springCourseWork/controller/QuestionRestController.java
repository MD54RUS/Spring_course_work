package com.example.springCourseWork.controller;

import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.service.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/question")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("create")
    public QuestionsItemDTO create(@RequestBody QuestionsItemDTO dto) {
        return questionService.createQuestion(dto);
    }
}
