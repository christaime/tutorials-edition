package org.mnc.tutorials.editor.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Domain {

    protected UUID id;

    protected LocalDateTime createdAt;
    protected UUID createdBy;

    protected LocalDateTime lastModificationAt;
    protected UUID lastModificationBy;

    public Domain(UUID createdBy) {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
    }

    public Domain(UUID id, UUID modifiedBy) {
        this.id = id;
        this.lastModificationAt = LocalDateTime.now();
        this.lastModificationBy = modifiedBy;
    }

    public Domain(UUID id, LocalDateTime createdAt, UUID createdBy, LocalDateTime lastModificationAt, UUID lastModificationBy) {
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
