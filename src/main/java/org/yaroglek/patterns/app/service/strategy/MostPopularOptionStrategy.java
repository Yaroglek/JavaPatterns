package org.yaroglek.patterns.app.service.strategy;

import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class MostPopularOptionStrategy implements ResultCalculationStrategy {
    @Override
    public Map<Question, Map<Option, Long>> calculateResults(Survey survey) {
        Map<Question, Map<Option, Long>> results = new HashMap<>();
        for (Question question : survey.getQuestions()) {
            Map<Option, Long> optionCounts = ResultCalculationStrategy.countOptions(survey, question);
            results.put(question, optionCounts);

            // Найти самый популярный вариант
            Option mostPopular = optionCounts.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(null);

            Map<Option, Long> mostPopularOption = new HashMap<>();
            if (mostPopular != null) {
                mostPopularOption.put(mostPopular, optionCounts.get(mostPopular));
            }

            results.put(question, mostPopularOption);
        }
        return results;
    }
}
