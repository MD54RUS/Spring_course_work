package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.AnswerItemDTO;
import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.QuestionRepository;
import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

    public QuestionServiceImpl(
            QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

  @Override
  public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
    Question question = new Question();
    question.setName(dto.name);
    questionRepository.save(question);

    for (AnswerItemDTO answerDTO : dto.answers) {
      Answer answer = new Answer();
      answer.setName(answerDTO.answerText);
      answer.setCorrect(answerDTO.isCorrect);
      answer.setQuestion(question);

      answerRepository.save(answer);
    }
    return new QuestionsItemDTO(question, answerRepository.findByQuestion(question));
  }

    @Override
    public QuestionsItemDTO editQuestion(QuestionsItemDTO dto) {
        Question question =
                questionRepository.findById(Long.parseLong(dto.id)).orElseThrow(RuntimeException::new);
        questionRepository.delete(question);
        answerRepository.deleteByQuestion(question);
        return createQuestion(dto);
    }


    @Override
    public List<QuestionsItemDTO> getQuestionsNew() {
        return questionRepository.findAll().stream()
                .map(
                        question -> new QuestionsItemDTO(
                                question,
                                new ArrayList<Answer>(answerRepository.findByQuestion(question)), false))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Question> findAtPage(int pageIndex, int rowCount, Sort.Direction direction, String field) {
        PageRequest pageRequest = PageRequest.of(pageIndex, rowCount, direction, field);
        return questionRepository.findAll(pageRequest);
    }
}
