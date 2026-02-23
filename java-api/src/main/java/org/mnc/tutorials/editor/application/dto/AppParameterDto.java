package org.mnc.tutorials.editor.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AppParameterDto(
        String id,
        @NotBlank
        String key,
        @NotBlank
        String value,
        @Size(max = 500)
        String description
) {}
