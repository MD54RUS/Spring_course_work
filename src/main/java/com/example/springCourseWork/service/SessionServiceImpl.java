package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.SessionDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.SessionAnsweredRepository;
import com.example.springCourseWork.data.SessionRepository;
import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Session;
import com.example.springCourseWork.entity.SessionsAnswers;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private AnswerRepository answerRepository;
    private final SessionAnsweredRepository sesAnsRep;

    public SessionServiceImpl(
            SessionRepository sessionRepository,
            AnswerRepository answerRepository,
            SessionAnsweredRepository sesAnsRep) {
        this.sessionRepository = sessionRepository;
        this.answerRepository = answerRepository;
        this.sesAnsRep = sesAnsRep;
    }

    @Override
    public String createSession(SessionDTO dto) {
        Session session = new Session();
        Double sum = (double) dto.questionsList.size();
        Double res =
                dto.questionsList.stream()
                        .mapToDouble(
                                question -> {
                                    AtomicReference<Boolean> oneQuestDone = new AtomicReference<>(true);
                                    question.answersList.forEach(
                                            answerItemDTO -> {
                                                Answer answer =
                                                        answerRepository
                                                                .findById(Long.parseLong(answerItemDTO.id))
                                                                .orElse(new Answer());
                                                if (answerItemDTO.isSelected) {
                                                    sesAnsRep.save(new SessionsAnswers(session, answer));
                                                }
                                                if (answerItemDTO.isSelected != answer.getCorrect()) {
                                                    oneQuestDone.set(false);
                                                }
                                            });
                                    return oneQuestDone.get() ? 1L : 0L;
                                })
                        .sum();
        session.setInsertDate(new Date().getTime());
        session.setName(dto.name);
        session.setResult(Math.round(res / sum * 100));
        sessionRepository.save(session);
        return session.getResult().toString();
    }
}
