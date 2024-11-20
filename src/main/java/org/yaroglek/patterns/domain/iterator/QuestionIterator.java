package org.yaroglek.patterns.domain.iterator;

import org.yaroglek.patterns.domain.Question;

import java.util.List;

/**
 * Реализация паттерна Iterator.
 */
public class QuestionIterator implements SurveyIterator<Question> {
    private final List<Question> questions;
    private int position = 0;

    public QuestionIterator(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean hasNext() {
        return position < questions.size();
    }

    @Override
    public Question next() {
        if (!hasNext()) {
            return null;
        }
        return questions.get(position++);
    }

    @Override
    public boolean hasPrev() {
        return position > 0;
    }

    @Override
    public Question prev() {
        if (!hasPrev()) {
            return null;
        }
        return questions.get(--position);
    }
}

