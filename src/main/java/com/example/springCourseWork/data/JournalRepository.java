package com.example.springCourseWork.data;

import com.example.springCourseWork.entity.Journal;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, String> {}
