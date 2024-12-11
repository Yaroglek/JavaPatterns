package org.yaroglek.patterns.app.service.templatemethod;

import lombok.AllArgsConstructor;
import org.yaroglek.patterns.app.repository.AnswerRepository;
import org.yaroglek.patterns.domain.Answer;
import org.yaroglek.patterns.domain.Question;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.yaroglek.patterns.extern.logger.Logger;


/**
 * Реализация паттерна TemplateMethod
 */
@Component
@AllArgsConstructor
public abstract class SurveyAnalysis<T> {
    private AnswerRepository answerRepository;

    public final void analyzeSurvey(Long surveyId) {
        List<Answer> answers = loadAnswers(surveyId);
        if (answers.isEmpty()) {
            throw new IllegalStateException("Нет ответов для анализа опроса с ID: " + surveyId);
        }

        if (!validateAnswers(answers)) {
            throw new IllegalStateException("Некорректные данные в ответах опроса с ID: " + surveyId);
        }

        Map<Question, T> analysisResults = performAnalysis(answers);

        logResults(analysisResults);
    }

    protected List<Answer> loadAnswers(Long surveyId) {
        return answerRepository.findAnswersByResponse_Survey_Id(surveyId);
    }

    protected boolean validateAnswers(List<Answer> answers) {
        return answers.stream().noneMatch(answer -> answer.getOptions().isEmpty());
    }

    protected abstract Map<Question, T> performAnalysis(List<Answer> answers);

    protected void logResults(Map<Question, T> analysisResults) {
        analysisResults.forEach((question, result) -> Logger.getInstance().log("Вопрос: " + question.getQuestionText() + ", Результат: " + result));
    }
}

