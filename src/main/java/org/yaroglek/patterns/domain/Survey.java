package org.yaroglek.patterns.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaroglek.patterns.domain.iterator.QuestionIterator;
import org.yaroglek.patterns.domain.iterator.SurveyIterator;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Паттерн Prototype.
 */
@Entity
@Table(name = "surveys")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Survey {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "started")
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "ended")
    private LocalDateTime endDateTime;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    public Survey(Survey survey) {
        this.title = survey.title;
        this.description = survey.description;
        this.startDateTime = survey.startDateTime;
        this.endDateTime = survey.endDateTime;

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
}
