package org.yaroglek.patterns.extern.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.yaroglek.patterns.domain.enums.QuestionType;
import org.yaroglek.patterns.extern.dto.validation.ValueOfEnum;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionDTO  extends RepresentationModel<QuestionDTO> {
    private long id;

    @NotBlank
    private String questionText;

    @NotNull
    @ValueOfEnum(enumClass = QuestionType.class)
    private QuestionType type;

    private List<OptionDTO> options;
}
