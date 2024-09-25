package org.yaroglek.patterns.extern.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.Response;
import org.yaroglek.patterns.domain.User;
import org.yaroglek.patterns.extern.controller.UserController;
import org.yaroglek.patterns.extern.dto.UserDTO;

import java.util.stream.Collectors;

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
}
