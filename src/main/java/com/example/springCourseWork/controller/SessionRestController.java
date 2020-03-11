package com.example.springCourseWork.controller;

import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.service.QuestionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/session")
public class SessionRestController {
    private QuestionService questionService;

    public SessionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("questions-new")
    public List<QuestionsItemDTO> getQuestionsForSessions() {
        return questionService.getQuestionsNew();
    }
}
