package org.yaroglek.patterns.extern.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaroglek.patterns.app.service.OptionService;
import org.yaroglek.patterns.domain.Option;
import org.yaroglek.patterns.extern.assembler.OptionAssembler;
import org.yaroglek.patterns.extern.dto.OptionDTO;

@RestController
@RequestMapping("/options")
@AllArgsConstructor
public class OptionController {
    private final OptionAssembler optionAssembler;
    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<OptionDTO> createOption(@RequestBody @Valid OptionDTO optionDTO) {
        Option newOption = optionAssembler.toEntity(optionDTO);

        optionService.createOption(newOption);

        return new ResponseEntity<>(optionAssembler.toModel(newOption), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionDTO> getOptionById(@PathVariable long id) {
        Option option = optionService.getOptionById(id);
        return ResponseEntity.ok(optionAssembler.toModel(option));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable long id) {
        optionService.deleteOptionById(id);
        return ResponseEntity.noContent().build();
    }
}
