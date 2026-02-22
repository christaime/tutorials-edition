package org.mnc.tutorials.editor.application.usecase;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.utils.AlreadyExistsException;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateFieldOfStudyUseCase {

    private final FieldOfStudyRepository repository;

    public CreateFieldOfStudyUseCase(FieldOfStudyRepository repository) {
        this.repository = repository;
    }

    public FieldOfStudy execute(FieldOfStudyDto dto, UUID creatorId) {
        repository.findByNameIgnoreCase(dto.name())
                .ifPresent(f -> { throw new AlreadyExistsException("Field of study already exists"); });

        FieldOfStudy field = new FieldOfStudy(creatorId, dto.name(), dto.description(), false);
        return repository.save(field, creatorId);
    }
}
