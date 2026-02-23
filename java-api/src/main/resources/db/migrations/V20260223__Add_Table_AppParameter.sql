-- Table for application parameters
CREATE TABLE IF NOT EXISTS app_parameters (
    id UUID PRIMARY KEY,
    param_key VARCHAR(100) NOT NULL UNIQUE,
    param_value TEXT NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    created_by UUID NOT NULL,
    last_modification_at TIMESTAMP,
    last_modification_by UUID
);

-- Create an index for fast lookups by key
CREATE INDEX idx_app_params_key ON app_parameters (param_key);