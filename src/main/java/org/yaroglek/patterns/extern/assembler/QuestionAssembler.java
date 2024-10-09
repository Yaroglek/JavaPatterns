package org.yaroglek.patterns.extern.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.Option;
import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.extern.dto.OptionDTO;
import org.yaroglek.patterns.extern.dto.QuestionDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionAssembler extends RepresentationModelAssemblerSupport<Question, QuestionDTO> {
    private final OptionAssembler optionAssembler;

    @Autowired
    public QuestionAssembler(OptionAssembler optionAssembler) {
        super(Question.class, QuestionDTO.class);
        this.optionAssembler = optionAssembler;
    }

    @Override
    public QuestionDTO toModel(Question question) {
        QuestionDTO questionDTO = instantiateModel(question);

        questionDTO.setId(question.getId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setType(question.getType());

        questionDTO.setOptions(question.getOptions().stream()
                .map(optionAssembler::toModel)
                .collect(Collectors.toList()));

        return questionDTO;
    }

    public Question toEntity(QuestionDTO questionDTO) {
        Question question = new Question();

        question.setQuestionText(questionDTO.getQuestionText());
        question.setType(questionDTO.getType());

        question.setOptions(questionDTO.getOptions().stream()
                .map(optionAssembler::toEntity)
                .collect(Collectors.toList()));

        return question;
    }
}
