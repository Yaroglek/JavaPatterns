package org.yaroglek.patterns.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaroglek.patterns.app.repository.UserRepository;
import org.yaroglek.patterns.domain.User;

import org.yaroglek.patterns.extern.logger.Logger;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не может быть null");
        }
        try {
            userRepository.save(user);
            Logger.getInstance().log(String.format("Создан пользователь с ID %s", user.getId()));
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка при сохранении нового пользователя", e);
        }
    }

    public User getUserById(long userId) {
        var searchedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с ID " + userId + " не найден"));
        Logger.getInstance().log(String.format("Пользователь с ID %s найден", userId));
        return searchedUser;
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
        Logger.getInstance().log(String.format("Пользователь с ID %s удален", userId));
    }
}
