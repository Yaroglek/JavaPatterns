package org.yaroglek.patterns.domain.state;

import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.domain.Survey;

public class ArchivedState implements SurveyState {
    @Override
    public void publish(Survey survey) {
        throw new UnsupportedOperationException("Нельзя опубликовать архивированный опрос");
    }

    @Override
    public void close(Survey survey) {
        throw new UnsupportedOperationException("Нельзя закрыть архивированный опрос");
    }

    @Override
    public void archive(Survey survey) {
        throw new UnsupportedOperationException("Опрос уже архивирован");
    }

    @Override
    public void addQuestion(Survey survey, Question question) {
        throw new UnsupportedOperationException("Нельзя добавлять вопросы в архивированный опрос");
    }
}
