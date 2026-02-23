package org.mnc.tutorials.editor.domain.model.admin;

import org.mnc.tutorials.editor.domain.model.Domain;

import java.util.UUID;

public class AppParameter extends Domain {

    private String key;
    private String value;
    private String description;

    public AppParameter(UUID createdBy, String key, String value, String description) {
        super(createdBy);
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