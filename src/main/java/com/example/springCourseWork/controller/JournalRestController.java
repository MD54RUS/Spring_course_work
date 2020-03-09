package com.example.springCourseWork.controller;

import com.example.springCourseWork.controller.dto.JournalEntityDTO;
import com.example.springCourseWork.controller.dto.JournalItemDTO;
import com.example.springCourseWork.controller.dto.JournalRequestDTO;
import com.example.springCourseWork.controller.dto.JournalResultDTO;
import com.example.springCourseWork.service.JournalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/journal")
public class JournalRestController {

  private final JournalService journalService;

  public JournalRestController(JournalService journalService) {
    this.journalService = journalService;
  }

  @GetMapping("{id}")
  public JournalEntityDTO getJournalEntity(@PathVariable String id) {
    return new JournalEntityDTO(journalService.getJournal(id));
  }

  @PutMapping("{id}/rows")
  public JournalResultDTO getRows(@PathVariable String id, @RequestBody JournalRequestDTO req) {
    List<? extends JournalItemDTO> collect = journalService.getJournalRows(id, req);
    return new JournalResultDTO(collect.size(), collect);
  }
}
