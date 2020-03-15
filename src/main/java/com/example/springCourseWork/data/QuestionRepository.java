package com.example.springCourseWork.data;

import com.example.springCourseWork.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long>,
        PagingAndSortingRepository<Question, Long> {
    List<Question> findAll();

    List<Question> findByNameContainingIgnoreCase(String search, Pageable pageable);
}
