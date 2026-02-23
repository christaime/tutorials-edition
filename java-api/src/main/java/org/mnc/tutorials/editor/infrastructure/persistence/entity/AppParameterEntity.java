package org.mnc.tutorials.editor.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "app_parameters")
public class AppParameterEntity extends BaseEntity {

    @Column(name = "param_key", nullable = false, unique = true, updatable = false)
    private String key;

    @Column(name = "param_value", nullable = false)
    private String value;

    @Column(name = "description")
    private String description;

    public AppParameterEntity(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public AppParameterEntity(UUID createdBy, String key, String value, String description) {
        super(createdBy);
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public AppParameterEntity(UUID id, LocalDateTime createdAt, UUID createdBy, LocalDateTime lastModificationAt, UUID lastModificationBy, String key, String value, String description) {
        super(id, createdAt, createdBy, lastModificationAt, lastModificationBy);
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}