package org.mnc.tutorials.editor.application.usecase.field;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.application.utils.AlreadyExistsException;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateFieldOfStudyUseCase {

    private final FieldOfStudyRepository repository;
    private final FieldOfStudyDtoMapper mapper;

    public CreateFieldOfStudyUseCase(FieldOfStudyRepository repository, FieldOfStudyDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FieldOfStudyDto execute(FieldOfStudyDto dto, UUID creatorId) {
        repository.findByNameIgnoreCase(dto.name())
                .ifPresent(f -> { throw new AlreadyExistsException("Field of study already exists"); });

        FieldOfStudy field = new FieldOfStudy(creatorId, dto.name(), dto.description(), false);
        field.setLastModificationBy(creatorId);
        field.setLastModificationAt(field.getCreatedAt());
        field = repository.save(field);
        return mapper.toDto(field);
    }
}
