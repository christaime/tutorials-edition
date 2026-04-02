package org.mnc.tutorials.editor.application.usecase.field;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.application.utils.NotFoundException;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateFieldOfStudyUseCase {

    private final FieldOfStudyRepository repository;
    private final FieldOfStudyDtoMapper mapper;

    public UpdateFieldOfStudyUseCase(FieldOfStudyRepository repository, FieldOfStudyDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FieldOfStudyDto execute(UUID id, FieldOfStudyDto dto, UUID modifierId) {
        FieldOfStudy existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Field not found"));

        // Business Rule: Cannot update if already approved
        if (existing.isApproved()) {
            throw new IllegalStateException("Cannot update an approved Field of Study.");
        }

        mapper.updateDomain(existing,dto,modifierId);

        existing = repository.save(existing);

        return mapper.toDto(existing);
    }
}
