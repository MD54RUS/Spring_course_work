package com.example.springCourseWork.data;

import com.example.springCourseWork.entity.Answer;
import com.example.springCourseWork.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findByQuestion(Question question);
}
