package org.mnc.tutorials.editor.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "fields_of_study")
@Getter
@Setter
public class FieldOfStudyEntity extends BaseEntity {

    @Column(name = "name",nullable = false, length = 255, unique = true)
    private String name;

    @Column(name = "description",nullable = true, length = 500)
    private String description;

    @Column(name = "is_approved",nullable = false)
    private boolean approved = false;

    public FieldOfStudyEntity(){
        super();
    }

    public FieldOfStudyEntity(String name, String description, boolean approved, UUID createdBy) {
        super(createdBy);
        this.name = name;
        this.description = description;
        this.approved = approved;
    }

    public FieldOfStudyEntity(String name, String description, boolean approved) {
        super();
        this.name = name;
        this.description = description;
        this.approved = approved;
    }

}
