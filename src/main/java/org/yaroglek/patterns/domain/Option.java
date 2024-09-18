package org.yaroglek.patterns.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "options")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
