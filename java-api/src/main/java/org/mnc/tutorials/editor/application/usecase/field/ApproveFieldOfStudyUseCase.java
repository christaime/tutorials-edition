package org.mnc.tutorials.editor.application.usecase.field;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.application.utils.NotFoundException;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ApproveFieldOfStudyUseCase {
    private final FieldOfStudyRepository repository;
    private final FieldOfStudyDtoMapper mapper;

    public ApproveFieldOfStudyUseCase(FieldOfStudyRepository repository, FieldOfStudyDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FieldOfStudyDto execute(UUID id, UUID adminId) {
        FieldOfStudy field = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Field not found"));

        if (!field.isApproved()) {
            field.setApproved(true);
            field.setLastModificationBy(adminId);
            field.setLastModificationAt(LocalDateTime.now());
            field = repository.save(field);
        }
        return mapper.toDto(field);
    }
}
