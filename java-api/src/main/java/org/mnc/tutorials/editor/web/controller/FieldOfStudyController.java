package org.mnc.tutorials.editor.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.dto.PageResult;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.application.usecase.field.*;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/fields-of-study")
@Tag(name = "Field of Study", description = "Endpoints for managing Tutorial Categories")
public class FieldOfStudyController {

    private final CreateFieldOfStudyUseCase createUseCase;
    private final UpdateFieldOfStudyUseCase updateUseCase;
    private final DeleteFieldOfStudyUseCase deleteUseCase;
    private final ApproveFieldOfStudyUseCase approveUseCase;
    private final SearchFieldOfStudyUseCase searchUseCase;
    private final FieldOfStudyDtoMapper dtoMapper;

    public FieldOfStudyController(CreateFieldOfStudyUseCase createUseCase, UpdateFieldOfStudyUseCase updateUseCase, DeleteFieldOfStudyUseCase deleteUseCase, ApproveFieldOfStudyUseCase approveUseCase, SearchFieldOfStudyUseCase searchUseCase, FieldOfStudyDtoMapper dtoMapper) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.approveUseCase = approveUseCase;
        this.searchUseCase = searchUseCase;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @Operation(summary = "Create a new field", description = "Adds a field if the name is unique. Initial status is unapproved.")
    public ResponseEntity<FieldOfStudyDto> create(@RequestBody FieldOfStudyDto dto, @RequestHeader("X-User-Id") UUID userId) {
        var domain = createUseCase.execute(dto, userId);
        return new ResponseEntity<>(dtoMapper.toDto(domain), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a field", description = "Updates are only allowed if the field is not yet approved.")
    public ResponseEntity<FieldOfStudyDto> update(@PathVariable UUID id, @RequestBody FieldOfStudyDto dto, @RequestHeader("X-User-Id") UUID userId) {
        var domain = updateUseCase.execute(id, dto, userId);
        return ResponseEntity.ok(dtoMapper.toDto(domain));
    }

    @PatchMapping("/{id}/approve")
    @Operation(summary = "Approve a field", description = "Admin action to make a field official.")
    public ResponseEntity<Void> approve(@PathVariable UUID id, @RequestHeader("X-User-Id") UUID userId) {
        approveUseCase.execute(id, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a field")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Search fields", description = "Supports filtering by name and approval status with pagination.")
    public ResponseEntity<PageResult<FieldOfStudyDto>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Boolean approved,
            @RequestParam(required = false) SortCriteria.Direction sortDirection,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var criteria = new FieldOfStudyCriteria(name,description, approved,new SortCriteria(sortDirection, sortBy), page, size);
        var domainPage = searchUseCase.execute(criteria);
        return ResponseEntity.ok(dtoMapper.toPageResult(domainPage));
    }
}
