package org.yaroglek.patterns.app.service.surveyservice;

import org.yaroglek.patterns.domain.Survey;

public abstract class SurveyDecorator implements SurveyService {
    private final SurveyService baseService;

    protected SurveyDecorator(SurveyService baseService) {
        this.baseService = baseService;
    }

    @Override
    public Survey createSurvey(Survey survey) {
        return baseService.createSurvey(survey);
    }

    @Override
    public Survey getSurveyById(long id) {
        return baseService.getSurveyById(id);
    }

    @Override
    public void deleteSurveyById(long id) {
        baseService.deleteSurveyById(id);
    }
}
