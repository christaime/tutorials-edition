package org.mnc.tutorials.editor.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.dto.PageResult;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.application.usecase.field.*;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.mnc.tutorials.editor.infrastructure.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/fields-of-study")
@Tag(name = "Field of Study", description = "Endpoints for managing Tutorial Categories")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN','MODERATOR')")
public class FieldOfStudyController {

    private final CreateFieldOfStudyUseCase createUseCase;
    private final UpdateFieldOfStudyUseCase updateUseCase;
    private final DeleteFieldOfStudyUseCase deleteUseCase;
    private final ApproveFieldOfStudyUseCase approveUseCase;
    private final SearchFieldOfStudyUseCase searchUseCase;

    @PostMapping
    @Operation(summary = "Create a new field", description = "Adds a field if the name is unique. Initial status is unapproved.")
    public ResponseEntity<FieldOfStudyDto> create(@RequestBody FieldOfStudyDto dto) {

        log.info("User {} is creating field of study '{}'", SecurityUtils.getUserLogin(), dto.name());

        var fieldOfStudyCreatedDto = createUseCase.execute(dto, SecurityUtils.getUserId());
        return new ResponseEntity<>(fieldOfStudyCreatedDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a field", description = "Updates are only allowed if the field is not yet approved.")
    public ResponseEntity<FieldOfStudyDto> update(@PathVariable UUID id, @RequestBody FieldOfStudyDto dto) {
        log.info("User {} is updating field of study '{}'", SecurityUtils.getUserLogin(), dto.name());
        var fieldUpdated = updateUseCase.execute(id, dto, SecurityUtils.getUserId());
        return ResponseEntity.ok(fieldUpdated);
    }

    @PatchMapping("/{id}/approve")
    @Operation(summary = "Approve a field", description = "Admin action to make a field official.")
    public ResponseEntity<FieldOfStudyDto> approve(@PathVariable UUID id) {
        log.info("User {} is approving field of study with id '{}'", SecurityUtils.getUserLogin(), id);
        var fieldApprouved = approveUseCase.execute(id, SecurityUtils.getUserId());
        return ResponseEntity.ok(fieldApprouved);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a field")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        log.info("User {} is deleting field of study with id '{}'", SecurityUtils.getUserLogin(), id);
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("permitAll()")
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

        log.info("User {} is searching field of studies", SecurityUtils.getUserLogin());

        var criteria = new FieldOfStudyCriteria(name,description, approved,new SortCriteria(sortDirection, sortBy), page, size);
        var result = searchUseCase.execute(criteria);
        return ResponseEntity.ok(result);
    }
}
