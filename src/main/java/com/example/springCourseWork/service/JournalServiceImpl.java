package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.JournalItemDTO;
import com.example.springCourseWork.controller.dto.JournalRequestDTO;
import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.JournalRepository;
import com.example.springCourseWork.data.QuestionRepository;
import com.example.springCourseWork.entity.BaseEntity;
import com.example.springCourseWork.entity.Journal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

  private final JournalRepository journalRepository;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;
  public static final String QUESTIONS_JOURNAL_ID = "questions";

  public JournalServiceImpl(
      JournalRepository journalRepository,
      QuestionRepository questionRepository,
      AnswerRepository answerRepository) {
    this.journalRepository = journalRepository;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @Override
  public Journal getJournal(String id) {
    return journalRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException(String.format("Не найден журнал с Id %s", id)));
  }

  @Override
  public List<? extends JournalItemDTO> getJournalRows(String id, JournalRequestDTO req) {
    List<? extends JournalItemDTO> collection;
    switch (id) {
      case QUESTIONS_JOURNAL_ID:
        collection =
            getCollection(
                req.search,
                questionRepository::findByNameContainingIgnoreCase,
                q -> new QuestionsItemDTO(q, answerRepository.findByQuestion(q)));
        break;
      default:
        throw new RuntimeException();
    }

    return collection;
  }

  private <T extends BaseEntity, U extends JournalItemDTO> List<U> getCollection(
      String search, Function<String, List<T>> finder, Function<T, U> mapper) {
    return finder.apply(search).stream().map(mapper).collect(Collectors.toList());
  }
}
