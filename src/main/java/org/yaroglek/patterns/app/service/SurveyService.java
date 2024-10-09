package org.yaroglek.patterns.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaroglek.patterns.app.repository.SurveyRepository;
import org.yaroglek.patterns.domain.Survey;
import org.yaroglek.patterns.extern.logger.Logger;

@Service
@AllArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public Survey createSurvey(Survey survey) {
        if (survey == null) {
            throw new IllegalArgumentException("Опрос не может быть null");
        }
        try {
            surveyRepository.save(survey);
            Logger.getInstance().log(String.format("Создан опрос с ID %s", survey.getId()));
            return survey;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении опроса", e);
        }
    }

    public Survey getSurveyById(long surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Опрос с ID " + surveyId + " не найден"));
    }

    public void deleteSurveyById(long surveyId) {
        surveyRepository.deleteById(surveyId);
        Logger.getInstance().log(String.format("Опрос с ID %s удален", surveyId));
    }
}
