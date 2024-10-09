package org.yaroglek.patterns.extern.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaroglek.patterns.app.service.UserService;
import org.yaroglek.patterns.domain.User;
import org.yaroglek.patterns.extern.assembler.UserAssembler;
import org.yaroglek.patterns.extern.dto.UserDTO;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserAssembler userAssembler;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        User newUser = userAssembler.toEntity(userDTO);

        userService.createUser(newUser);

        return new ResponseEntity<>(userAssembler.toModel(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}