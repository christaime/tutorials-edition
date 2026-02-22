package org.mnc.tutorials.editor.application.usecase;

import org.mnc.tutorials.editor.application.utils.NotFoundException;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApproveFieldOfStudyUseCase {
    private final FieldOfStudyRepository repository;

    public ApproveFieldOfStudyUseCase(FieldOfStudyRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id, UUID adminId) {
        FieldOfStudy field = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Field not found"));

        if (field.isApproved()) {
            return; // Already approved, no action needed
        }

        field.setApproved(true);
        repository.save(field, adminId);
    }
}
