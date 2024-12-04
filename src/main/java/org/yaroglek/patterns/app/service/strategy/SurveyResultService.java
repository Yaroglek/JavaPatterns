package org.yaroglek.patterns.app.service.strategy;

import org.springframework.stereotype.Service;
import org.yaroglek.patterns.domain.Option;
import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.domain.Survey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SurveyResultService {
    private final Map<String, ResultCalculationStrategy> strategies = new HashMap<>();

    private ResultCalculationStrategy strategy;

    public SurveyResultService(List<ResultCalculationStrategy> strategyList) {
        for (ResultCalculationStrategy strategy : strategyList) {
            strategies.put(strategy.getClass().getSimpleName(), strategy);
        }
    }

    public void addStrategy(String strategyName, ResultCalculationStrategy strategy) {
        if (strategies.containsKey(strategyName)) {
            throw new IllegalArgumentException("Стратегия с именем " + strategyName + " уже существует");
        }
        strategies.put(strategyName, strategy);
    }

    public void removeStrategy(String strategyName) {
        if (!strategies.containsKey(strategyName)) {
            throw new IllegalArgumentException("Стратегия с именем " + strategyName + " не найдена");
        }
        strategies.remove(strategyName);
    }

    public void setStrategy(String strategyName) {
        if (!strategies.containsKey(strategyName)) {
            throw new IllegalArgumentException("Стратегия с именем " + strategyName + " не найдена");
        }
        this.strategy = strategies.get(strategyName);
    }

    public Map<Question, Map<Option, Long>> calculateResults(Survey survey) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия подсчета результатов не установлена");
        }
        return strategy.calculateResults(survey);
    }

    public List<String> getAvailableStrategies() {
        return List.copyOf(strategies.keySet());
    }
}
