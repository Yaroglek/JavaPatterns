package org.yaroglek.patterns.app.service.templatemethod;

import org.springframework.stereotype.Component;
import org.yaroglek.patterns.app.repository.AnswerRepository;
import org.yaroglek.patterns.domain.Answer;
import org.yaroglek.patterns.domain.Option;
import org.yaroglek.patterns.domain.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PopularOptionAnalysis extends SurveyAnalysis<Map<Option, Long>> {
    public PopularOptionAnalysis(AnswerRepository answerRepository) {
        super(answerRepository);
    }

    @Override
    protected Map<Question, Map<Option, Long>> performAnalysis(List<Answer> answers) {
        Map<Question, Map<Option, Long>> analysisResults = new HashMap<>();

        for (Answer answer : answers) {
            Map<Option, Long> optionCounts = analysisResults.computeIfAbsent(answer.getQuestion(), _ -> new HashMap<>());
            for (Option option : answer.getOptions()) {
                optionCounts.put(option, optionCounts.getOrDefault(option, 0L) + 1);
            }
        }

        return analysisResults;
    }
}