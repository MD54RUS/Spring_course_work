package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.JournalItemDTO;
import com.example.springCourseWork.controller.dto.JournalRequestDTO;
import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.controller.dto.SessionItemDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.JournalRepository;
import com.example.springCourseWork.data.QuestionRepository;
import com.example.springCourseWork.data.SessionRepository;
import com.example.springCourseWork.entity.BaseEntity;
import com.example.springCourseWork.entity.Journal;
import org.springframework.data.domain.PageRequest;
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
    private final SessionRepository sessionRepository;
    public static final String QUESTIONS_JOURNAL_ID = "questions";
    public static final String SESSIONS_JOURNAL_ID = "sessions";

    public JournalServiceImpl(
            JournalRepository journalRepository,
            QuestionRepository questionRepository,
            AnswerRepository answerRepository, SessionRepository sessionRepository) {
        this.journalRepository = journalRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.sessionRepository = sessionRepository;
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
      System.out.println(String.format("page = %d, pageSize = %d", req.page, req.pageSize));
      PageRequest page = PageRequest.of(req.page - 1, req.pageSize);
      switch (id) {
          case QUESTIONS_JOURNAL_ID:
              collection =
                      getCollection(
                              req.search,
                              (String search) -> questionRepository.findByNameContainingIgnoreCase(search, page),
                              q -> new QuestionsItemDTO(q, answerRepository.findByQuestion(q)));
              break;
          case SESSIONS_JOURNAL_ID:
              collection = getCollection(
                      req.search,
                      (String name) -> sessionRepository.findByNameContainingIgnoreCase(name, page),
                      SessionItemDTO::new);
            break;
        default:
            throw new RuntimeException(String.format("Не найден журнал с ID %s", id));
    }

    return collection;
  }

  private <T extends BaseEntity, U extends JournalItemDTO> List<U> getCollection(
      String search, Function<String, List<T>> finder, Function<T, U> mapper) {
    return finder.apply(search).stream().map(mapper).collect(Collectors.toList());
  }
}
