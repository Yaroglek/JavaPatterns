package org.yaroglek.patterns.extern.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaroglek.patterns.app.service.QuestionService;
import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.extern.assembler.QuestionAssembler;
import org.yaroglek.patterns.extern.dto.QuestionDTO;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionAssembler questionAssembler;

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody @Valid QuestionDTO questionDTO) {
        Question newQuestion = questionAssembler.toEntity(questionDTO);

        questionService.createQuestion(newQuestion);

        return new ResponseEntity<>(questionAssembler.toModel(newQuestion), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(questionAssembler.toModel(question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.noContent().build();
    }
}
