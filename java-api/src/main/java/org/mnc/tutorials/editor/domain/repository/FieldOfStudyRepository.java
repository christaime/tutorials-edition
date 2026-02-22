package org.mnc.tutorials.editor.domain.repository;

import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FieldOfStudyRepository {
    FieldOfStudy save(FieldOfStudy fieldOfStudy, UUID performedBy);
    Optional<FieldOfStudy> findById(UUID id);
    Optional<FieldOfStudy> findByNameIgnoreCase(String name);
    DomainPage<FieldOfStudy> findByCriteria(FieldOfStudyCriteria criteria);
    void deleteById(UUID id);
}
