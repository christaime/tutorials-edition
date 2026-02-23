package org.mnc.tutorials.editor.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mnc.tutorials.editor.application.dto.AppParameterDto;
import org.mnc.tutorials.editor.application.mapper.AppParameterDtoMapper;
import org.mnc.tutorials.editor.application.usecase.ManageAppParameterUseCase;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/parameters")
@Tag(name = "Application Parameters", description = "Management of system-wide configuration keys")
public class AppParameterController {

    private final ManageAppParameterUseCase manageAppParameterUseCase;
    private final AppParameterDtoMapper mapper;

    public AppParameterController(ManageAppParameterUseCase manageAppParameterUseCase, AppParameterDtoMapper mapper) {
        this.manageAppParameterUseCase = manageAppParameterUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Set or Update a parameter", description = "Creates a parameter if it doesn't exist, or updates its value if it does.")
    public ResponseEntity<Void> setParameter(
            @RequestBody AppParameterDto dto,
            @RequestHeader("X-User-Id") UUID userId) {

        manageAppParameterUseCase.setParameter(mapper.toDomain(dto),userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{key}")
    @Operation(summary = "Get a parameter by key", description = "Returns the value and metadata of a specific configuration key.")
    public ResponseEntity<AppParameterDto> getParameter(@PathVariable String key) {
        return manageAppParameterUseCase.getParameter(key)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all parameters", description = "Returns a list of all system configurations sorted by criteria.")
    public ResponseEntity<List<AppParameterDto>> getAllParameters(
            @RequestParam(required = false, defaultValue = "ASC") SortCriteria.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "key") String sortBy) {

        List<AppParameter> domains = manageAppParameterUseCase.getParameterList(new SortCriteria(sortDirection,sortBy));

        List<AppParameterDto> dtos = domains.stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{key}")
    @Operation(summary = "Delete a parameter", description = "Removes a configuration key from the system.")
    public ResponseEntity<Void> deleteParameter(@PathVariable String key) {
        manageAppParameterUseCase.deleteParameter(key);
        return ResponseEntity.noContent().build();
    }
}
