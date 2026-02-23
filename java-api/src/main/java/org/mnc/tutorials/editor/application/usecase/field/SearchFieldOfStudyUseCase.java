package org.mnc.tutorials.editor.application.usecase.field;

import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchFieldOfStudyUseCase {

    private final FieldOfStudyRepository repository;

    public SearchFieldOfStudyUseCase(FieldOfStudyRepository repository) {
        this.repository = repository;
    }

    public DomainPage<FieldOfStudy> execute(FieldOfStudyCriteria criteria) {
        return repository.findByCriteria(criteria);
    }
}
