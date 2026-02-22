package org.mnc.tutorials.editor.infrastructure.persistence.mapper;

import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.FieldOfStudyEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class FieldOfStudyMapper {

    public FieldOfStudy toDomain(FieldOfStudyEntity entity) {
        if (entity == null) return null;
        return new FieldOfStudy(entity.getId(),entity.getName(),entity.getDescription(),entity.isApproved()
                ,entity.getCreatedAt(),entity.getCreatedBy(),entity.getLastModificationAt(),entity.getLastModificationBy());
    }

    public FieldOfStudyEntity toEntity(FieldOfStudy domain) {
        if (domain == null) return null;
        FieldOfStudyEntity entity = new FieldOfStudyEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setApproved(domain.isApproved());
        entity.setLastModificationBy(domain.getLastModificationBy());
        entity.setLastModificationAt(domain.getLastModificationAt());
        return entity;
    }

    public FieldOfStudyEntity fromDomain(UUID createdBy, FieldOfStudy domain){
        return new FieldOfStudyEntity(domain.getName(), domain.getDescription(), domain.isApproved(), createdBy);
    }

    public void updateEntity(FieldOfStudyEntity entity,FieldOfStudy domain, UUID modifiedBy){
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setApproved(domain.isApproved());
        entity.setLastModificationAt(LocalDateTime.now());
        entity.setLastModificationBy(modifiedBy);
    }

    public DomainPage<FieldOfStudy> toDomainPage(Page<FieldOfStudyEntity> entityPage) {
        if (entityPage == null) {
            return null;
        }
        List<FieldOfStudy> domainList = entityPage.getContent()
                .stream()
                .map(this::toDomain)
                .toList();

        return new DomainPage<>(
                domainList,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }
}
