package org.mnc.tutorials.editor.application.usecase.field;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.dto.PageResult;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchFieldOfStudyUseCase {

    private final FieldOfStudyRepository repository;
    private final FieldOfStudyDtoMapper mapper;

    public SearchFieldOfStudyUseCase(FieldOfStudyRepository repository, FieldOfStudyDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PageResult<FieldOfStudyDto> execute(FieldOfStudyCriteria criteria) {
        DomainPage<FieldOfStudy> result = repository.findByCriteria(criteria);
        return mapper.toPageResult(result);
    }
}
