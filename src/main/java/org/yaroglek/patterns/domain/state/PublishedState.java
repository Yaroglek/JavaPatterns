package org.yaroglek.patterns.domain.state;

import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.domain.Survey;
import org.yaroglek.patterns.extern.logger.Logger;

public class PublishedState implements SurveyState {
    @Override
    public void publish(Survey survey) {
        throw new UnsupportedOperationException("Опрос уже опубликован");
    }

    @Override
    public void close(Survey survey) {
        survey.setState(new ClosedState());
        Logger.getInstance().log("Опрос закрыт");
    }

    @Override
    public void archive(Survey survey) {
        throw new UnsupportedOperationException("Нельзя архивировать опубликованный опрос. Сначала закройте его");
    }

    @Override
    public void addQuestion(Survey survey, Question question) {
        throw new UnsupportedOperationException("Нельзя добавлять вопросы в опубликованный опрос");
    }
}
