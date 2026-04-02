package org.mnc.tutorials.editor.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.mnc.tutorials.editor.domain.model.admin.AppParameterKey;

public record AppParameterDto(
        String id,
        @NotBlank
        AppParameterKey key,
        @NotBlank
        String value,
        @Size(max = 500)
        String description
) {}
