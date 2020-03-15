package com.example.springCourseWork.service;

import com.example.springCourseWork.SpringCourseWorkApplication;
import com.example.springCourseWork.controller.dto.AnswerItemDTO;
import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.QuestionRepository;
import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Question;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCourseWorkApplication.class)
class QuestionServiceImplTest {

  @Autowired
  QuestionService questionService;

  @Autowired
  QuestionRepository questionRepository;

  @Autowired
  AnswerRepository answerRepository;

  private QuestionsItemDTO dto;

  @BeforeEach
  void setUp() {
    dto = new QuestionsItemDTO();
    dto.name = "testQuestion";
    AnswerItemDTO answer1 = new AnswerItemDTO();
    answer1.answerText = "Correct answer";
    answer1.isCorrect = true;
    AnswerItemDTO answer2 = new AnswerItemDTO();
    answer2.answerText = "Uncorrected answer";
    answer2.isCorrect = false;
    List<AnswerItemDTO> answerListDTO = new LinkedList<>();
    answerListDTO.add(answer1);
    answerListDTO.add(answer2);
    dto.answers = answerListDTO;
    questionService.createQuestion(dto);
  }

  @Test
  @DirtiesContext
  void createQuestion() {
//    Assert.assertEquals(0, questionRepository.findAll().size());
//    questionService.createQuestion(dto);
    Assert.assertEquals(1, questionRepository.findAll().size());
    Question question = questionRepository.findAll().get(0);
    Assert.assertEquals("testQuestion", question.getName());
    List<Answer> resAnswers = answerRepository.findByQuestion(question);
    Assert.assertEquals(
            2,
            resAnswers.stream()
                    .mapToInt(
                            value -> (value.getName().equals(dto.answers.get(0).answerText)
                                    || value.getName().equals(dto.answers.get(1).answerText))
                                    ? 1
                                    : 0)
                    .sum());
  }

  @Test
  @DirtiesContext
  void editQuestion() {
//    questionService.createQuestion(dto);
    Question origQuestion = questionRepository.findAll().get(0);
    Assert.assertEquals("testQuestion", origQuestion.getName());
    dto.id = origQuestion.getId().toString();
    dto.name = "testQuestionEdit";
    questionService.editQuestion(dto);
    Assert.assertEquals(1, questionRepository.findAll().size());
    Question question = questionRepository.findAll().get(0);
    Assert.assertEquals("testQuestionEdit", question.getName());
  }

  @Test
  void getQuestionsNew() {
//    questionService.createQuestion(dto);
    Assert.assertEquals(1, questionService.getQuestionsNew().size());
    int res =
            questionService.getQuestionsNew().stream()
                    .mapToInt(
                            q ->
                                    q.answers.stream()
                                            .mapToInt(
                                                    answerItemDTO -> {
                                                      if (answerItemDTO.isCorrect == null) return 0;
                                                      return 1;
                                                    })
                                            .sum())
                    .sum();
    Assert.assertEquals(0, res);
  }
}
