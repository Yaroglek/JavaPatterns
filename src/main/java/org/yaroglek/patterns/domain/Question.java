package org.yaroglek.patterns.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.yaroglek.patterns.domain.enums.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Паттерн Prototype.
 * Паттерн Static Factory Method.
 */
@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    public Question(Question question, @NonNull Survey survey) {
        this.questionText = question.getQuestionText();
        this.type = question.getType();
        this.survey = survey;

        if (options != null) {
            this.options = new ArrayList<>();
            for (Option option : options) {
                this.options.add(new Option(option, this));
            }
        }
    }

    public static Question polarQuestion(Survey survey, String questionText) {
        Question question = new Question();
        question.setSurvey(survey);
        question.setQuestionText(questionText);

        Option option1 = new Option();
        option1.setQuestion(question);
        option1.setText("Yes");

        Option option2 = new Option();
        option2.setQuestion(question);
        option2.setText("No");

        question.setOptions(Arrays.asList(option1, option2));

        return question;
    }
}
