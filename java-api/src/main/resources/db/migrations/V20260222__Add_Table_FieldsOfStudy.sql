-- Enable UUID extension if not already enabled (required for gen_random_uuid())
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE fields_of_study (
    -- BaseEntity Fields
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NOT NULL,
    last_modification_at TIMESTAMP,
    last_modification_by UUID,

    -- FieldOfStudyEntity Fields
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(500),
    is_approved BOOLEAN NOT NULL DEFAULT FALSE
);

-- Index for performance on searches by name (since we'll use findByNameIgnoreCase)
CREATE INDEX idx_fields_of_study_name ON fields_of_study (LOWER(name));

-- Index for searching by approval status
CREATE INDEX idx_fields_of_study_approved ON fields_of_study (is_approved);