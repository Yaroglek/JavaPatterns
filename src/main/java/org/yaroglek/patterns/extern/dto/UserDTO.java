package org.yaroglek.patterns.extern.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.yaroglek.patterns.domain.enums.UserRole;
import org.yaroglek.patterns.extern.dto.validation.ValueOfEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends RepresentationModel<UserDTO> {
    private long id;

    @NotBlank
    @Size(max = 100)
    private String username;

    @Email(regexp = "([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)")
    private String email;

    @NotBlank
    @Size(max = 1000)
    private String additionalInfo;

    @ValueOfEnum(enumClass = UserRole.class)
    private String role;
}
