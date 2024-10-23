package org.yaroglek.patterns.app.service.surveyservice;

import org.yaroglek.patterns.domain.Survey;

public interface SurveyService {
    Survey createSurvey(Survey survey);
    Survey getSurveyById(long id);
    void deleteSurveyById(long id);
}
