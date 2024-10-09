package org.yaroglek.patterns.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaroglek.patterns.app.repository.OptionRepository;
import org.yaroglek.patterns.app.repository.QuestionRepository;
import org.yaroglek.patterns.domain.Option;
import org.yaroglek.patterns.domain.Question;
import org.yaroglek.patterns.extern.logger.Logger;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Вопрос не может быть null");
        }
        try {
            questionRepository.save(question);
            Logger.getInstance().log(String.format("Создан вопрос с ID %s", question.getId()));
            return question;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении вопроса", e);
        }
    }

    public Question getQuestionById(long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Вопрос с ID " + questionId + " не найден"));
    }

    public void deleteQuestionById(long questionId) {
        questionRepository.deleteById(questionId);
        Logger.getInstance().log(String.format("Вопрос с ID %s удален", questionId));
    }
}
