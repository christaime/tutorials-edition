package org.mnc.tutorials.editor.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.mnc.tutorials.editor.domain.model.admin.AppParameterKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "app_parameters")
@Getter
@Setter
public class AppParameterEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "param_key", nullable = false, unique = true, updatable = false)
    private AppParameterKey key;

    @Column(name = "param_value", nullable = false)
    private String value;

    @Column(name = "description")
    private String description;

    public AppParameterEntity(){ super();}

    public AppParameterEntity(AppParameterKey key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public AppParameterEntity(UUID createdBy, AppParameterKey key, String value, String description) {
        super(createdBy);
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public AppParameterEntity(UUID id, LocalDateTime createdAt, UUID createdBy, LocalDateTime lastModificationAt, UUID lastModificationBy, AppParameterKey key, String value, String description) {
        super(id, createdAt, createdBy, lastModificationAt, lastModificationBy);
        this.key = key;
        this.value = value;
        this.description = description;
    }

}