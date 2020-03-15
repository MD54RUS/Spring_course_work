package com.example.springCourseWork.service;

import com.example.springCourseWork.SpringCourseWorkApplication;
import com.example.springCourseWork.controller.dto.AnsweredQuestionDTO;
import com.example.springCourseWork.controller.dto.SessionDTO;
import com.example.springCourseWork.controller.dto.SessionQuestionAnswer;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.JournalRepository;
import com.example.springCourseWork.data.QuestionRepository;
import com.example.springCourseWork.data.SessionRepository;
import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Journal;
import com.example.springCourseWork.entity.Question;
import com.example.springCourseWork.entity.Session;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.example.springCourseWork.service.JournalServiceImpl.QUESTIONS_JOURNAL_ID;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCourseWorkApplication.class)
class SessionServiceImplTest {

  @Autowired
  private JournalRepository journalRepository;

  @Autowired
  private JournalService journalService;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private SessionService sessionService;

  private SessionDTO dto = new SessionDTO();

  @BeforeEach
  void setUp() {
    Journal journal = new Journal();
    journal.setName("testJournal");
    journal.setId(QUESTIONS_JOURNAL_ID);
    journal.setDefaultPageSize(15L);
    journalRepository.save(journal);

    Question question = new Question();
    question.setName("testQuest1");
    questionRepository.save(question);

    Answer answer = new Answer();
    answer.setCorrect(true);
    answer.setQuestion(question);
    answer.setName("testAnswer1");
    answerRepository.save(answer);

    Answer answer2 = new Answer();
    answer2.setCorrect(false);
    answer2.setQuestion(question);
    answer2.setName("testAnswer2");
    answerRepository.save(answer2);

    Question question2 = new Question();
    question2.setName("testQuest2");
    questionRepository.save(question2);

    Answer answer3 = new Answer();
    answer3.setCorrect(true);
    answer3.setQuestion(question2);
    answer3.setName("testAnswer3");
    answerRepository.save(answer3);

    Answer answer4 = new Answer();
    answer4.setCorrect(false);
    answer4.setQuestion(question2);
    answer4.setName("testAnswer4");
    answerRepository.save(answer4);

    AnsweredQuestionDTO answeredQuest = new AnsweredQuestionDTO();
    SessionQuestionAnswer sessionQuestAns = new SessionQuestionAnswer();

    dto.name = "dtoTestName";

    SessionQuestionAnswer sesAns1 = new SessionQuestionAnswer();
    SessionQuestionAnswer sesAns2 = new SessionQuestionAnswer();
    SessionQuestionAnswer sesAns3 = new SessionQuestionAnswer();
    SessionQuestionAnswer sesAns4 = new SessionQuestionAnswer();
    sesAns1.id = answer.getId().toString();
    sesAns1.isSelected = true;
    sesAns2.id = answer2.getId().toString();
    sesAns2.isSelected = false;
    sesAns3.id = answer3.getId().toString();
    sesAns3.isSelected = false;
    sesAns4.id = answer4.getId().toString();
    sesAns4.isSelected = true;

    List<SessionQuestionAnswer> ansListDTO = new LinkedList<>();
    List<SessionQuestionAnswer> ansListDTO2 = new LinkedList<>();
    ansListDTO.add(sesAns1);
    ansListDTO.add(sesAns2);
    ansListDTO2.add(sesAns3);
    ansListDTO2.add(sesAns4);

    AnsweredQuestionDTO ansQuestDTO = new AnsweredQuestionDTO();
    ansQuestDTO.answersList = ansListDTO;
    AnsweredQuestionDTO ansQuestDTO2 = new AnsweredQuestionDTO();
    ansQuestDTO2.answersList = ansListDTO2;

    List<AnsweredQuestionDTO> ansListQuestDTO = new LinkedList<>();
    ansListQuestDTO.add(ansQuestDTO);
    ansListQuestDTO.add(ansQuestDTO2);
    dto.questionsList = ansListQuestDTO;

    sessionRepository.findAll();
  }

  @Test
  void createSessionTest() {
    Assert.assertEquals(0, ((Collection<?>) sessionRepository.findAll()).size());
    sessionService.createSession(dto);
    Assert.assertEquals(1, ((Collection<?>) sessionRepository.findAll()).size());
    Assert.assertEquals(1, sessionRepository.findByNameContainingIgnoreCase("dtoTestName").size());
    Session resSession = sessionRepository.findByNameContainingIgnoreCase("dtoTestName").get(0);
    Assert.assertEquals("dtoTestName", resSession.getName());
    Assert.assertEquals(50L, resSession.getResult().longValue());
  }
}
