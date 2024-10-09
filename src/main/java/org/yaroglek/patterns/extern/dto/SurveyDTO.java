package org.yaroglek.patterns.extern.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SurveyDTO  extends RepresentationModel<SurveyDTO> {
    private long id;

    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 1000)
    private String description;

    private List<QuestionDTO> questions;
}
