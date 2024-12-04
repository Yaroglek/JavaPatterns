package org.yaroglek.patterns.domain.state;

import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.domain.Survey;

public interface SurveyState {
    void publish(Survey survey);
    void close(Survey survey);
    void archive(Survey survey);
    void addQuestion(Survey survey, Question question);
}
