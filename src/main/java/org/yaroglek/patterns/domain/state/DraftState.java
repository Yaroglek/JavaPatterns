package org.yaroglek.patterns.domain.state;

import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.domain.Survey;
import org.yaroglek.patterns.extern.logger.Logger;

public class DraftState implements SurveyState {
    @Override
    public void publish(Survey survey) {
        survey.setState(new PublishedState());
        Logger.getInstance().log("Опрос опубликован");
    }

    @Override
    public void close(Survey survey) {
        throw new UnsupportedOperationException("Нельзя закрыть опрос, который еще не опубликован");
    }

    @Override
    public void archive(Survey survey) {
        throw new UnsupportedOperationException("Нельзя архивировать опрос, который еще не опубликован");
    }

    @Override
    public void addQuestion(Survey survey, Question question) {
        survey.getQuestions().add(question);
        Logger.getInstance().log("Добавлен вопрос в черновик");
    }
}