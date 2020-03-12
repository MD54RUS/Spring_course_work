package com.example.springCourseWork.data;

import com.example.springCourseWork.entity.SessionsAnswers;
import org.springframework.data.repository.CrudRepository;

public interface SessionAnsweredRepository extends CrudRepository<SessionsAnswers, Long> {
}
