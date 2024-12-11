package org.yaroglek.patterns.app.service.templatemethod;

import org.springframework.stereotype.Component;
import org.yaroglek.patterns.app.repository.AnswerRepository;
import org.yaroglek.patterns.domain.Answer;
import org.yaroglek.patterns.domain.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MissingAnswerAnalysis extends SurveyAnalysis<Boolean> {
    public MissingAnswerAnalysis(AnswerRepository answerRepository) {
        super(answerRepository);
    }

    @Override
    protected Map<Question, Boolean> performAnalysis(List<Answer> answers) {
        Map<Question, Boolean> analysisResults = new HashMap<>();

        for (Answer answer : answers) {
            analysisResults.put(answer.getQuestion(), answer.getOptions().isEmpty());
        }

        return analysisResults;
    }
}

