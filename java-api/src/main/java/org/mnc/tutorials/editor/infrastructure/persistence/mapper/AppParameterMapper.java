package org.mnc.tutorials.editor.infrastructure.persistence.mapper;

import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.AppParameterEntity;
import org.springframework.stereotype.Component;

@Component
public class AppParameterMapper {

    /**
     * Map from Entity (Database) to Domain (Business)
     */
    public AppParameter toDomain(AppParameterEntity entity) {
        if (entity == null) return null;

        AppParameter param = new AppParameter(
                entity.getId(),
                entity.getKey(),
                entity.getValue(),
                entity.getDescription()
        );
        param.setCreatedBy(entity.getCreatedBy());
        param.setCreatedAt(entity.getCreatedAt());
        param.setLastModificationAt(entity.getLastModificationAt());
        param.setLastModificationBy(entity.getLastModificationBy());
        return param;
    }

    /**
     * Map from Domain to Entity for Update (Existing Record)
     */
    public AppParameterEntity toEntity(AppParameter domain) {
        if (domain == null) return null;

        AppParameterEntity entity = new AppParameterEntity(domain.getKey(), domain.getValue(), domain.getDescription());

        // BaseEntity fields
        entity.setId(domain.getId());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setCreatedBy(domain.getCreatedBy());
        entity.setLastModificationAt(domain.getLastModificationAt());
        entity.setLastModificationBy(domain.getLastModificationBy());

        return entity;
    }
}
