package com.example.springCourseWork.controller;

import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.controller.dto.SessionDTO;
import com.example.springCourseWork.service.QuestionService;
import com.example.springCourseWork.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/session")
public class SessionRestController {
  private QuestionService questionService;
  private SessionService sessionService;

  public SessionRestController(QuestionService questionService, SessionService sessionService) {
    this.questionService = questionService;
    this.sessionService = sessionService;
  }

  @GetMapping("questions-new")
  public List<QuestionsItemDTO> getQuestionsForSessions() {
    return questionService.getQuestionsNew();
  }

  @PostMapping("")
  public String createSession(@RequestBody SessionDTO req) {
    return sessionService.createSession(req);
  }
}
