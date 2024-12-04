package org.yaroglek.patterns.app.service.strategy;

import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация паттерна Strategy
 */
@Component
public interface ResultCalculationStrategy {
    Map<Question, Map<Option, Long>> calculateResults(Survey survey);

    static Map<Option, Long> countOptions(Survey survey, Question question) {
        Map<Option, Long> optionCounts = new HashMap<>();
        for (Response response : survey.getResponses()) {
            for (Answer answer : response.getAnswers()) {
                if (answer.getQuestion().equals(question)) {
                    for (Option option : answer.getOptions()) {
                        optionCounts.put(option, optionCounts.getOrDefault(option, 0L) + 1);
                    }
                }
            }
        }

        return optionCounts;
    }
}