package org.yaroglek.patterns.app.service.surveyservice;

import org.yaroglek.patterns.domain.Survey;
import org.yaroglek.patterns.extern.logger.Logger;

/**
 * Реализация паттерна Decorator.
 */
public class LoggedSurveyService extends SurveyDecorator {
    protected LoggedSurveyService(SurveyService baseService) {
        super(baseService);
    }

    @Override
    public Survey createSurvey(Survey survey) {
        long startTime = System.currentTimeMillis();
        Survey result = super.createSurvey(survey);
        long endTime = System.currentTimeMillis();

        Logger.getInstance().log("Создание опроса заняло" + (endTime - startTime) + "мс");
        return result;
    }

    @Override
    public Survey getSurveyById(long id) {
        long startTime = System.currentTimeMillis();
        Survey result = super.getSurveyById(id);
        long endTime = System.currentTimeMillis();

        Logger.getInstance().log("Получение опроса из БД заняло " + (endTime - startTime) + "мс");

        return result;
    }

    @Override
    public void deleteSurveyById(long id) {
        long startTime = System.currentTimeMillis();
        super.deleteSurveyById(id);
        long endTime = System.currentTimeMillis();

        Logger.getInstance().log("Удаление опроса из БД заняло " + (endTime - startTime) + "мс");
    }
}
