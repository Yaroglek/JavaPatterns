package org.yaroglek.patterns.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.LinkedList;

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

    @OneToMany(mappedBy = "survey")
    private LinkedList<Question> questions;
}
