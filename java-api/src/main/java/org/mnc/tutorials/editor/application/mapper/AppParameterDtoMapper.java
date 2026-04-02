package org.mnc.tutorials.editor.application.mapper;

import org.mnc.tutorials.editor.application.dto.AppParameterDto;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;

public interface AppParameterDtoMapper {
    void update(AppParameter toUpdate, AppParameterDto newParameter) ;
    AppParameter toDomain(AppParameterDto dto);
    AppParameterDto toDto(AppParameter domain);
}
