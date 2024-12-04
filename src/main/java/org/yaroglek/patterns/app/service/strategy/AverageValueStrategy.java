package org.yaroglek.patterns.app.service.strategy;

import org.yaroglek.patterns.domain.*;
import org.yaroglek.patterns.domain.enums.QuestionType;

import java.util.HashMap;
import java.util.Map;

public class AverageValueStrategy implements ResultCalculationStrategy {
    @Override
    public Map<Question, Map<Option, Long>> calculateResults(Survey survey) {
        Map<Question, Map<Option, Long>> results = new HashMap<>();
        for (Question question : survey.getQuestions()) {
            if (question.getType() != QuestionType.NUMERIC) {
                continue;
            }

            long total = 0;
            long count = 0;

            for (Response response : survey.getResponses()) {
                for (Answer answer : response.getAnswers()) {
                    if (answer.getQuestion().equals(question)) {
                        for (Option option : answer.getOptions()) {
                            try {
                                total += Integer.parseInt(option.getText());
                                count++;
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Невозможно преобразовать опцию в число: " + option.getText());
                            }
                        }
                    }
                }
            }

            double average = count > 0 ? (double) total / count : 0;
            Map<Option, Long> averageMap = new HashMap<>();
            averageMap.put(new Option(), 0L); // Псевдо-опция для среднего значения

            results.put(question, averageMap);
        }
        return results;
    }
}
