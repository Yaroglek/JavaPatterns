package org.yaroglek.patterns.extern.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaroglek.patterns.app.service.surveyservice.SurveyServiceImpl;
import org.yaroglek.patterns.domain.Survey;
import org.yaroglek.patterns.extern.assembler.SurveyAssembler;
import org.yaroglek.patterns.extern.dto.SurveyDTO;

@RestController
@RequestMapping("/surveys")
@AllArgsConstructor
public class SurveyController {
    private final SurveyServiceImpl surveyService;
    private final SurveyAssembler surveyAssembler;

    @PostMapping
    public ResponseEntity<SurveyDTO> createSurvey(@RequestBody @Valid SurveyDTO surveyDTO) {
        Survey newSurvey = surveyAssembler.toEntity(surveyDTO);
        surveyService.createSurvey(newSurvey);
        return new ResponseEntity<>(surveyAssembler.toModel(newSurvey), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyDTO> getSurveyById(@PathVariable long id) {
        Survey survey = surveyService.getSurveyById(id);
        return ResponseEntity.ok(surveyAssembler.toModel(survey));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable long id) {
        surveyService.deleteSurveyById(id);
        return ResponseEntity.noContent().build();
    }
}
