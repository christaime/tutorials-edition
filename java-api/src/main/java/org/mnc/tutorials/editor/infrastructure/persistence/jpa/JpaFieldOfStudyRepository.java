package org.mnc.tutorials.editor.infrastructure.persistence.jpa;

import org.mnc.tutorials.editor.infrastructure.persistence.entity.FieldOfStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaFieldOfStudyRepository extends JpaRepository<FieldOfStudyEntity, UUID>, JpaSpecificationExecutor<FieldOfStudyEntity> {
    Optional<FieldOfStudyEntity> findByNameIgnoreCase(String name);
}
