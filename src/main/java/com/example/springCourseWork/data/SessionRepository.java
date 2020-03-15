package com.example.springCourseWork.data;

import com.example.springCourseWork.entity.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long>,
        PagingAndSortingRepository<Session, Long> {
    List<Session> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
