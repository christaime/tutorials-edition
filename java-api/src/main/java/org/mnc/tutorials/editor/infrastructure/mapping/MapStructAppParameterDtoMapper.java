package org.mnc.tutorials.editor.infrastructure.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mnc.tutorials.editor.application.dto.AppParameterDto;
import org.mnc.tutorials.editor.application.mapper.AppParameterDtoMapper;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;

@Mapper(componentModel = "spring")
public interface MapStructAppParameterDtoMapper extends AppParameterDtoMapper {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "key", ignore = true)
    void update(@MappingTarget AppParameter domain, AppParameterDto dto) ;

    @Override
    AppParameter toDomain(AppParameterDto dto);

    @Override
    AppParameterDto toDto(AppParameter domain);

}
