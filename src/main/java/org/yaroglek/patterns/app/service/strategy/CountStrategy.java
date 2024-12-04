package org.yaroglek.patterns.app.service.strategy;

import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountStrategy implements ResultCalculationStrategy {
    @Override
    public Map<Question, Map<Option, Long>> calculateResults(Survey survey) {
        Map<Question, Map<Option, Long>> results = new HashMap<>();
        for (Question question : survey.getQuestions()) {
            Map<Option, Long> optionCounts = ResultCalculationStrategy.countOptions(survey, question);
            results.put(question, optionCounts);
        }
        return results;
    }


}
