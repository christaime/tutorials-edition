package org.mnc.tutorials.editor.infrastructure.persistence.jpa;

import org.mnc.tutorials.editor.domain.model.admin.AppParameterKey;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.AppParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAppParameterRepository extends JpaRepository<AppParameterEntity, UUID> {

    Optional<AppParameterEntity> findByKey(AppParameterKey key);

    @Modifying
    @Transactional
    void deleteByKey(AppParameterKey key);
}
