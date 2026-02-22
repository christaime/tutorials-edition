package org.mnc.tutorials.editor.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected UUID id;

    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastModificationAt() {
        return lastModificationAt;
    }

    public void setLastModificationAt(LocalDateTime lastModificationAt) {
        this.lastModificationAt = lastModificationAt;
    }

    public UUID getLastModificationBy() {
        return lastModificationBy;
    }

    public void setLastModificationBy(UUID lastModificationBy) {
        this.lastModificationBy = lastModificationBy;
    }
}
