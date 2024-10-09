package org.yaroglek.patterns.extern.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class OptionDTO extends RepresentationModel<OptionDTO> {
    private long id;

    @NotBlank
    private String text;
}
