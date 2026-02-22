package org.mnc.tutorials.editor.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FieldOfStudyDto(
        String id,
        @NotBlank
        @Size(max = 255)
        String name,
        @NotBlank
        @Size(max = 500)
        String description,
        boolean approved) {
}
