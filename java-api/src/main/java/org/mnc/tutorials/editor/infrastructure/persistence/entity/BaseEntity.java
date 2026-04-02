package org.mnc.tutorials.editor.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    protected UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    protected UUID createdBy;

    @Column(name = "last_modification_at", nullable = true)
    protected LocalDateTime lastModificationAt;

    @Column(name = "last_modification_by", nullable = true)
    protected UUID lastModificationBy;

    public BaseEntity(){}

    public BaseEntity(UUID createdBy){
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
    }

    public BaseEntity(UUID id, LocalDateTime createdAt, UUID createdBy, LocalDateTime lastModificationAt, UUID lastModificationBy) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.lastModificationAt = lastModificationAt;
        this.lastModificationBy = lastModificationBy;
    }

}
