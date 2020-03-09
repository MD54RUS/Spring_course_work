package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.AnswerItemDTO;
import com.example.springCourseWork.controller.dto.QuestionsItemDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.QuestionRepository;
import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Question;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);
        questionRepository.save(question);

        for (AnswerItemDTO answerDTO : dto.answers){
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);

            answerRepository.save(answer);
        }
        return new QuestionsItemDTO(question, answerRepository.findByQuestion(question));
    }
}
