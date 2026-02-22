package org.mnc.tutorials.editor.application.usecase.field;

import org.mnc.tutorials.editor.application.utils.NotFoundException;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteFieldOfStudyUseCase {

    private final FieldOfStudyRepository repository;

    public DeleteFieldOfStudyUseCase(FieldOfStudyRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Field not found"));
        repository.deleteById(id);
    }
}
