package org.mnc.tutorials.editor.domain.model.tutorial;

import org.mnc.tutorials.editor.domain.model.Domain;
import org.mnc.tutorials.editor.domain.model.admin.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Tutorial extends Domain {

    private User author;
    private List<FieldOfStudy> fieldsOfStudy;
    private String title;
    private String description;
    private TutorialContent content;

    public Tutorial(UUID createdBy) {
        super(createdBy);
    }

    public Tutorial(UUID id, UUID modifiedBy) {
        super(id, modifiedBy);
    }

    public Tutorial(UUID id, LocalDateTime createdAt, UUID createdBy, LocalDateTime lastModificationAt, UUID lastModificationBy) {
        super(id, createdAt, createdBy, lastModificationAt, lastModificationBy);
    }
}
