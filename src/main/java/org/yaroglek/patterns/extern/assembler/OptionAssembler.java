package org.yaroglek.patterns.extern.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.yaroglek.patterns.domain.Option;
import org.yaroglek.patterns.extern.dto.OptionDTO;

@Component
public class OptionAssembler extends RepresentationModelAssemblerSupport<Option, OptionDTO> {
    public OptionAssembler() {
        super(Option.class, OptionDTO.class);
    }

    @Override
    public OptionDTO toModel(Option option) {
        OptionDTO optionDTO = instantiateModel(option);

        optionDTO.setId(option.getId());
        optionDTO.setText(option.getText());

        return optionDTO;
    }

    public Option toEntity(OptionDTO optionDTO) {
        Option option = new Option();

        option.setText(optionDTO.getText());

        return option;
    }
}
