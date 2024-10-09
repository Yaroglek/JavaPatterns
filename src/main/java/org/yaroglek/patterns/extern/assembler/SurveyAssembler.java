package org.yaroglek.patterns.extern.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.Survey;
import org.yaroglek.patterns.extern.dto.QuestionDTO;
import org.yaroglek.patterns.extern.dto.SurveyDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SurveyAssembler extends RepresentationModelAssemblerSupport<Survey, SurveyDTO> {
    private final QuestionAssembler questionAssembler;

    @Autowired
    public SurveyAssembler(Class<?> controllerClass, Class<SurveyDTO> resourceType, QuestionAssembler questionAssembler) {
        super(controllerClass, resourceType);
        this.questionAssembler = questionAssembler;
    }

    @Override
    public SurveyDTO toModel(Survey survey) {
        SurveyDTO surveyDTO = new SurveyDTO();

        surveyDTO.setId(survey.getId());
        surveyDTO.setTitle(survey.getTitle());
        surveyDTO.setDescription(survey.getDescription());

        surveyDTO.setQuestions(survey.getQuestions().stream()
                .map(questionAssembler::toModel)
                .collect(Collectors.toList()));

        return surveyDTO;
    }

    public Survey toEntity(SurveyDTO surveyDTO) {
        Survey survey = new Survey();

        survey.setTitle(surveyDTO.getTitle());
        survey.setDescription(surveyDTO.getDescription());
        survey.setQuestions(surveyDTO.getQuestions().stream()
                .map(questionAssembler::toEntity)
                .collect(Collectors.toCollection(LinkedList::new)));

        return survey;
    }
}
