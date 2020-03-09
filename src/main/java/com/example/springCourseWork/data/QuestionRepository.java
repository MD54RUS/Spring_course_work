package com.example.springCourseWork.data;

import com.example.springCourseWork.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findByNameContainingIgnoreCase(String search);
}
