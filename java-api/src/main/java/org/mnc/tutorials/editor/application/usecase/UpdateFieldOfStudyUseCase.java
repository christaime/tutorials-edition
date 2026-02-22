package org.mnc.tutorials.editor.application.usecase;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.application.utils.NotFoundException;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.mnc.tutorials.editor.infrastructure.persistence.mapper.FieldOfStudyMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateFieldOfStudyUseCase {

    private final FieldOfStudyRepository repository;
    private final FieldOfStudyDtoMapper fieldOfStudyDtoMapper;

    public UpdateFieldOfStudyUseCase(FieldOfStudyRepository repository, FieldOfStudyDtoMapper fieldOfStudyDtoMapper) {
        this.repository = repository;
        this.fieldOfStudyDtoMapper = fieldOfStudyDtoMapper;
    }

    public FieldOfStudy execute(UUID id, FieldOfStudyDto dto, UUID modifierId) {
        FieldOfStudy existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Field not found"));

        // Business Rule: Cannot update if already approved
        if (existing.isApproved()) {
            throw new IllegalStateException("Cannot update an approved Field of Study.");
        }

        fieldOfStudyDtoMapper.updateDomain(existing,dto,modifierId);

        return repository.save(existing, modifierId);
    }
}
