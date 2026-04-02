package org.mnc.tutorials.editor.domain.model.admin;

import org.mnc.tutorials.editor.domain.model.Domain;

import java.util.UUID;

public class AppParameter extends Domain {

    private AppParameterKey key;
    private String value;
    private String description;

    public AppParameter(){}

    public AppParameter(UUID createdBy, AppParameterKey key, String value, String description) {
        super(createdBy);
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public AppParameterKey getKey() {
        return key;
    }

    public void setKey(AppParameterKey key) {
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