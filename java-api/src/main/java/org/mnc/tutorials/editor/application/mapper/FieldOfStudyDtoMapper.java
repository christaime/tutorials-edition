package org.mnc.tutorials.editor.application.mapper;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.dto.PageResult;
import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import java.util.UUID;

public interface FieldOfStudyDtoMapper {

    FieldOfStudy toDomain(UUID createdBy,FieldOfStudyDto dto);

    void updateDomain(FieldOfStudy domain,FieldOfStudyDto dto,UUID modifiedBy);

    FieldOfStudyDto toDto(FieldOfStudy domain);

    PageResult<FieldOfStudyDto> toPageResult(DomainPage<FieldOfStudy> domainPage);
}
