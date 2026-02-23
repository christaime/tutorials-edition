package org.mnc.tutorials.editor.domain.repository;

import org.mnc.tutorials.editor.domain.model.admin.AppParameter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppParameterRepository {
    AppParameter save(AppParameter parameter);
    Optional<AppParameter> findByKey(String key);
    List<AppParameter> findAll(SortCriteria sorting);
    void deleteByKey(String key);
}
