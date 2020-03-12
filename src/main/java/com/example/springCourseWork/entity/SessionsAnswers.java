package com.example.springCourseWork.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SessionsAnswers extends BaseEntity {

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    @JoinColumn(name = "marked_answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    public SessionsAnswers(Session session, Answer answer) {
        this.session = session;
        this.answer = answer;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
