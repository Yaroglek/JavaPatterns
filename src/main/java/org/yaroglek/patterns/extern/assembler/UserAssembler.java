package org.yaroglek.patterns.extern.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.User;
import org.yaroglek.patterns.domain.enums.UserRole;
import org.yaroglek.patterns.extern.controller.UserController;
import org.yaroglek.patterns.extern.dto.UserDTO;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<User, UserDTO> {
    public UserAssembler() {
        super(UserController.class, UserDTO.class);
    }

    @Override
    public UserDTO toModel(User user) {
        UserDTO userDTO = instantiateModel(user);

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAdditionalInfo(user.getAdditionalInfo());
        userDTO.setRole(String.valueOf(user.getRole()));

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAdditionalInfo(userDTO.getAdditionalInfo());
        user.setRole(UserRole.valueOf(userDTO.getRole()));

        return user;
    }
}
