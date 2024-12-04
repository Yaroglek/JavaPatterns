package org.yaroglek.patterns.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Паттерн Prototype.
 */
@Entity
@Table(name = "options")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public Option(Option option, @NonNull Question question) {
        this.text = option.text;
        this.question = question;
    }
}
