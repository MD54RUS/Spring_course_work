package com.example.springCourseWork;

import ch.qos.logback.classic.joran.JoranConfigurator;
import com.example.springCourseWork.data.JournalRepository;
import com.example.springCourseWork.entity.Journal;
import com.example.springCourseWork.service.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringCourseWorkApplication {

	@Autowired
	private JournalRepository journalRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringCourseWorkApplication.class, args);
	}

	@PostConstruct
	private void initData(){
		Journal journal = new Journal();
		journal.setId(JournalServiceImpl.QUESTIONS_JOURNAL_ID);
		journal.setName("Вопросы");
		journal.setDefaultPageSize(15L);
		journalRepository.save(journal);

	}
}
