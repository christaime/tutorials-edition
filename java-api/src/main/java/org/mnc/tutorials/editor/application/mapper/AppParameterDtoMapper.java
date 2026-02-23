package org.mnc.tutorials.editor.application.mapper;

import org.mnc.tutorials.editor.application.dto.AppParameterDto;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppParameterDtoMapper {

    public void update(AppParameter toUpdate, AppParameter newParameter) {
        if(toUpdate != null && newParameter != null){
            toUpdate.setValue(newParameter.getValue());
            toUpdate.setDescription(newParameter.getDescription());
        }
    }

    /**
     * Map from DTO (Web) to Domain (Business) for Creation
     */
    public AppParameter toDomain(AppParameterDto dto) {
        if (dto == null) return null;

        return new AppParameter(
                dto.id() != null ? UUID.fromString(dto.id()) : null                      ,
                dto.key(),
                dto.value(),
                dto.description()
        );
    }

    /**
     * Map from Domain to DTO for API Responses
     */
    public AppParameterDto toDto(AppParameter domain) {
        if (domain == null) return null;

        return new AppParameterDto(
                domain.getId() != null ? domain.getId().toString() : null,
                domain.getKey(),
                domain.getValue(),
                domain.getDescription()
        );
    }

    /**
     * Update an existing Domain object with DTO data
     * (As requested in your previous logic for void updates)
     */
    public void updateDomain(AppParameter domain, AppParameterDto dto) {
        if (dto == null || domain == null) return;

        // Note: Usually we don't allow updating the 'key' as it's the unique identifier
        domain.setValue(dto.value());
        domain.setDescription(dto.description());
    }
}
