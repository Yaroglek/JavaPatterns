package org.yaroglek.patterns.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaroglek.patterns.app.repository.OptionRepository;
import org.yaroglek.patterns.domain.Option;
import org.yaroglek.patterns.extern.logger.Logger;

@Service
@AllArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    public Option createOption(Option option) {
        if (option == null) {
            throw new IllegalArgumentException("Опция не может быть null");
        }
        try {
            optionRepository.save(option);
            Logger.getInstance().log(String.format("Создана опция с ID %s", option.getId()));
            return option;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении опции", e);
        }
    }

    public Option getOptionById(long optionId) {
        return optionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Опция с ID " + optionId + " не найдена"));
    }

    public void deleteOptionById(long optionId) {
        optionRepository.deleteById(optionId);
        Logger.getInstance().log(String.format("Опция с ID %s удалена", optionId));
    }
}
