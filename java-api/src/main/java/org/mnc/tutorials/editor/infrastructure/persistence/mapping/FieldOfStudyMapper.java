package org.mnc.tutorials.editor.infrastructure.persistence.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.FieldOfStudyEntity;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FieldOfStudyMapper {

    FieldOfStudy toDomain(FieldOfStudyEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "lastModificationAt", source = "lastModificationAt")
    @Mapping(target = "lastModificationBy", source = "lastModificationBy")
    FieldOfStudyEntity toEntity(FieldOfStudy domain);

    default DomainPage<FieldOfStudy> toDomainPage(Page<FieldOfStudyEntity> entityPage) {
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
