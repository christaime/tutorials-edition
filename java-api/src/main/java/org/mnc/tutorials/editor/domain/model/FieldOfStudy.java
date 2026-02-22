package org.mnc.tutorials.editor.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class FieldOfStudy extends Domain {
    private String name;
    private String description;
    private boolean approved;

    public FieldOfStudy(UUID createdBy,String name, String description, boolean approved) {
        super(createdBy);
        this.name = name;
        this.description = description;
        this.approved = approved;
    }

    public FieldOfStudy(UUID id,String name, String description, boolean approved,UUID modifiedBy) {
        super(id,modifiedBy);
        this.name = name;
        this.description = description;
        this.approved = approved;
    }

    public FieldOfStudy(UUID id, String name, String description, boolean approved, LocalDateTime createdAt, UUID createdBy, LocalDateTime lastModificationAt, UUID lastModificationBy) {
        super(id, createdAt, createdBy, lastModificationAt, lastModificationBy);
        this.name = name;
        this.description = description;
        this.approved = approved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
