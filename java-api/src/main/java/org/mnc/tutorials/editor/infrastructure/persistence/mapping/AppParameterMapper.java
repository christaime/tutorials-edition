package org.mnc.tutorials.editor.infrastructure.persistence.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.AppParameterEntity;

@Mapper(componentModel = "spring")
public interface AppParameterMapper {

    AppParameter toDomain(AppParameterEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "lastModificationAt", source = "lastModificationAt")
    @Mapping(target = "lastModificationBy", source = "lastModificationBy")
    AppParameterEntity toEntity(AppParameter domain);
}
