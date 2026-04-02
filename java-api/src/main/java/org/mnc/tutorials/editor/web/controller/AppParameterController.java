package org.mnc.tutorials.editor.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mnc.tutorials.editor.application.dto.AppParameterDto;
import org.mnc.tutorials.editor.application.mapper.AppParameterDtoMapper;
import org.mnc.tutorials.editor.application.usecase.ManageAppParameterUseCase;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.domain.model.admin.AppParameterKey;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.mnc.tutorials.editor.infrastructure.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/parameters")
@Tag(name = "Application Parameters", description = "Management of system-wide configuration keys")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AppParameterController {

    private final ManageAppParameterUseCase manageAppParameterUseCase;

    @PostMapping
    @Operation(summary = "Set or Update a parameter", description = "Creates a parameter if it doesn't exist, or updates its value if it does.")
    public ResponseEntity<Void> setParameter( @RequestBody AppParameterDto dto) {

        log.info("User {} is setting parameter with key {}", SecurityUtils.getUserLogin(), dto.key());

        manageAppParameterUseCase.setParameter(dto, SecurityUtils.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{key}")
    @Operation(summary = "Get a parameter by key", description = "Returns the value and metadata of a specific configuration key.")
    public ResponseEntity<AppParameterDto> getParameter(@PathVariable AppParameterKey key) {

        log.info("User {} is getting parameter with key {}", SecurityUtils.getUserLogin(), key);

        return manageAppParameterUseCase.getParameter(key)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all parameters", description = "Returns a list of all system configurations sorted by criteria.")
    public ResponseEntity<List<AppParameterDto>> getAllParameters(
            @RequestParam(required = false, defaultValue = "ASC") SortCriteria.Direction sortDirection,
            @RequestParam(required = false, defaultValue = "key") String sortBy) {

        log.info("User {} is getting all parameters", SecurityUtils.getUserLogin());
        List<AppParameterDto> response = manageAppParameterUseCase.getParameterList(new SortCriteria(sortDirection,sortBy));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{key}")
    @Operation(summary = "Delete a parameter", description = "Removes a configuration key from the system.")
    public ResponseEntity<Void> deleteParameter(@PathVariable AppParameterKey key) {
        log.info("User {} is deleting parameter with key {}", SecurityUtils.getUserLogin(), key);
        manageAppParameterUseCase.deleteParameter(key);
        return ResponseEntity.noContent().build();
    }
}
