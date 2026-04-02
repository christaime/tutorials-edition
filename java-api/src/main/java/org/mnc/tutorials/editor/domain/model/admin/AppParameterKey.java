package org.mnc.tutorials.editor.domain.model.admin;

public enum AppParameterKey {

    TUTORIAL_PUBLISH_AUTO_APPROVE(
            "Auto-Approve Tutorials",
            ParameterType.BOOLEAN,
            "Decide if new tutorials need manual admin review before going live."
    ),

    TUTORIAL_MAX_PER_USER(
            "Max Drafts per User",
            ParameterType.INTEGER,
            "Limits how many 'Draft' tutorials a single user can have at once."
    ),

    AUTH_REGISTRATION_ENABLED(
            "Enable Registration",
            ParameterType.BOOLEAN,
            "Toggle to enable or disable new user sign-ups on the platform."
    ),

    MAINTENANCE_MODE(
            "Maintenance Mode",
            ParameterType.BOOLEAN,
            "Redirects all non-admin users to a 'Coming Back Soon' page."
    ),

    MENTOR_MAX_SIMULTANEOUS_TUTORIALS(
            "Max Simultaneous Mentorships",
            ParameterType.INTEGER,
            "Limits the number of tutorials a user can actively mentor to ensure quality."
    );

    private final String friendlyName;
    private final ParameterType type;
    private final String description;

    AppParameterKey(String friendlyName, ParameterType type, String description) {
        this.friendlyName = friendlyName;
        this.type = type;
        this.description = description;
    }

    public enum ParameterType {
        BOOLEAN, INTEGER, STRING, DECIMAL
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public ParameterType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
