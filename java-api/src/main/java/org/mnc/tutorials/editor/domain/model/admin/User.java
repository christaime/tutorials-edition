package org.mnc.tutorials.editor.domain.model.admin;

import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;

import java.util.List;
import java.util.Locale;

public class User {
    private String email;
    private String fullName;
    private String comments;
    private Locale language;
    private UserStatus status; // ACTIVE, PENDING_VERIFICATION, BANNED
    private List<UserRole> roles;  // ADMIN, MODERATOR, USER

    // Specific to Tutorial Editor logic
    private List<FieldOfStudy> fieldsOfInterest;
    private List<FieldOfStudy> fieldsToMentor;
    private int maxSimultaneousMentorships;

    // Business Logic: Can this user accept more mentees?
    public boolean canAcceptMentorship(int currentActiveCount) {
        return currentActiveCount < maxSimultaneousMentorships && status == UserStatus.ACTIVE;
    }

    public enum UserStatus{
        ACTIVE,
        PENDING_VERIFICATION,
        BANNED
    }
}
