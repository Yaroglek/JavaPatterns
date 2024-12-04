package org.yaroglek.patterns.domain.state;

import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.domain.Survey;
import org.yaroglek.patterns.extern.logger.Logger;

public class ClosedState implements SurveyState {
    @Override
    public void publish(Survey survey) {
        throw new UnsupportedOperationException("Нельзя снова опубликовать закрытый опрос");
    }

    @Override
    public void close(Survey survey) {
        throw new UnsupportedOperationException("Опрос уже закрыт");
    }

    @Override
    public void archive(Survey survey) {
        survey.setState(new ArchivedState());
        Logger.getInstance().log("Опрос архивирован");
    }

    @Override
    public void addQuestion(Survey survey, Question question) {
        throw new UnsupportedOperationException("Нельзя добавлять вопросы в закрытый опрос");
    }
}
