package org.mnc.tutorials.editor.application.mapper;

import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.dto.PageResult;
import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.FieldOfStudyEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class FieldOfStudyDtoMapper {

    public FieldOfStudy toDomain(UUID createdBy,FieldOfStudyDto dto) {
        if (dto == null) return null;
        var now = LocalDateTime.now();
        return new FieldOfStudy(UUID.fromString(dto.id()),dto.name(),dto.description(),dto.approved()
                ,now,createdBy,now,createdBy);
    }

    public void updateDomain(FieldOfStudy domain,FieldOfStudyDto dto,UUID modifiedBy) {
        if (dto != null && domain != null) {
            domain.setName(dto.name());
            domain.setDescription(dto.description());
            domain.setLastModificationAt(LocalDateTime.now());
            domain.setLastModificationBy(modifiedBy);
        }
    }

    public FieldOfStudyDto toDto(FieldOfStudy domain) {
        if (domain == null) return null;
        return new FieldOfStudyDto(domain.getId() != null ? domain.getId().toString() : null,
                domain.getName(), domain.getDescription(), domain.isApproved());
    }

    public PageResult<FieldOfStudyDto> toPageResult(DomainPage<FieldOfStudy> domainPage) {
        if (domainPage == null) {
            return null;
        }
        List<FieldOfStudyDto> dtoList = domainPage.content()
                .stream()
                .map(this::toDto)
                .toList();

        return new PageResult<>(
                dtoList,
                domainPage.pageNumber(),
                domainPage.pageSize(),
                domainPage.totalElements(),
                domainPage.totalPages()
        );
    }
}
