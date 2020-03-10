package com.example.springCourseWork.data;

import com.example.springCourseWork.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> findByNameContainingIgnoreCase(String name);
}
