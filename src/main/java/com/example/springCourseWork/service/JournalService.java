package com.example.springCourseWork.service;

import com.example.springCourseWork.controller.dto.JournalItemDTO;
import com.example.springCourseWork.controller.dto.JournalRequestDTO;
import com.example.springCourseWork.entity.Journal;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JournalService {
    List<? extends JournalItemDTO> getJournalRows(String id, JournalRequestDTO req);

    Journal getJournal(String id);
}
