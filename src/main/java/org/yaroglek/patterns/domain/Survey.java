package org.yaroglek.patterns.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaroglek.patterns.domain.iterator.QuestionIterator;
import org.yaroglek.patterns.domain.iterator.SurveyIterator;
import org.yaroglek.patterns.domain.state.DraftState;
import org.yaroglek.patterns.domain.state.SurveyState;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Паттерн Prototype.
 * Паттерн State.
 */
@Entity
@Table(name = "surveys")
@AllArgsConstructor
@Data
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Transient
    private SurveyState state;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "started")
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "ended")
    private LocalDateTime endDateTime;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "survey")
    private List<Response> responses;

    public Survey() {
        this.state = new DraftState();
    }

    public Survey(Survey survey) {
        this.title = survey.title;
        this.description = survey.description;
        this.startDateTime = survey.startDateTime;
        this.endDateTime = survey.endDateTime;
        this.state = survey.state;

        if (questions != null) {
            this.questions = new LinkedList<>();
            for (Question question : questions) {
                this.questions.add(new Question(question, this));
            }
        }
    }

    public SurveyIterator<Question> getQuestionIterator() {
        return new QuestionIterator(questions);
    }

    public void changeState(SurveyState state) {
        this.state = state;
    }

    public void publish() {
        state.publish(this);
    }

    public void close() {
        state.close(this);
    }

    public void archive() {
        state.archive(this);
    }

    public void addQuestion(Question question) {
        state.addQuestion(this, question);
    }
}
