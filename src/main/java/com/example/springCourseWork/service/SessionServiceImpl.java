package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.SessionDTO;
import com.example.springCourseWork.data.AnswerRepository;
import com.example.springCourseWork.data.SessionRepository;
import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private AnswerRepository answerRepository;

    public SessionServiceImpl(
            SessionRepository sessionRepository, AnswerRepository answerRepository) {
        this.sessionRepository = sessionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public String createSession(SessionDTO dto) {
        System.out.println(dto.name);
        System.out.println(dto.questionsList);
        System.out.println(dto.questionsList.get(0).answersList.get(0).id);
        Double sum = (double) dto.questionsList.size();
        Double res =
                dto.questionsList.stream()
                        .mapToDouble(
                                question -> {
                                    AtomicReference<Boolean> oneQuestDone = new AtomicReference<>(true);
                                    question.answersList.forEach(
                                            answerItemDTO -> {
                                                if (answerItemDTO.isSelected //Дичь, а не условие
                                                        != answerRepository
                                                        .findById(Long.parseLong(answerItemDTO.id))
                                                        .orElse(new Answer())
                                                        .getCorrect()) {
                                                    oneQuestDone.set(false);
                                                }
                                            });
                                    if (oneQuestDone.get()) {
                                        return 1L;
                                    } else return 0L;
                                })
                        .sum();
        Session session = new Session();
        session.setInsertDate(new Date().getTime());
        session.setName(dto.name);
        session.setResult(Math.round(res / sum * 100));
        sessionRepository.save(session);
        return session.getResult().toString();
    }
}
