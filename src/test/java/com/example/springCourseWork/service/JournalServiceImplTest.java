package com.example.springCourseWork.service;

import com.example.springCourseWork.SpringCourseWorkApplication;
import com.example.springCourseWork.controller.dto.JournalRequestDTO;
import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.JournalRepository;
import com.example.springCourseWork.data.QuestionRepository;
import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Journal;
import com.example.springCourseWork.entity.Question;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.springCourseWork.service.JournalServiceImpl.QUESTIONS_JOURNAL_ID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCourseWorkApplication.class)
public class JournalServiceImplTest {

  @Autowired
  private JournalRepository journalRepository;

  @Autowired
  private JournalService journalService;

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  @Before
  public void setUp() {
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

    Question question2 = new Question();
    question2.setName("testQuest2");
    questionRepository.save(question2);
    Answer answer2 = new Answer();
    answer2.setCorrect(true);
    answer2.setQuestion(question2);
    answer2.setName("testAnswer2");
    answerRepository.save(answer2);
  }

  @Test
  public void getJournal() {
    Assert.assertNotNull(journalRepository.findById(QUESTIONS_JOURNAL_ID).orElse(null));
    Assert.assertEquals(
            journalService.getJournal(QUESTIONS_JOURNAL_ID).getName(),
            journalRepository.findById(QUESTIONS_JOURNAL_ID).orElse(null).getName());
  }

  @Test
  public void getJournalRows() {
    JournalRequestDTO journalRequestDTO = new JournalRequestDTO("", 0, 15);
    List<QuestionsItemDTO> result =

            (List<QuestionsItemDTO>) journalService.getJournalRows(QUESTIONS_JOURNAL_ID, journalRequestDTO);
    Assert.assertEquals(
            result.size(),
            result.stream().filter(y -> y.name.equals("testQuest1") || y.name.equals("testQuest2")).count());
  }
}
