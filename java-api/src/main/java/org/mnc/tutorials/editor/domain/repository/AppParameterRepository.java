package org.mnc.tutorials.editor.domain.repository;

import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.domain.model.admin.AppParameterKey;

import java.util.List;
import java.util.Optional;

public interface AppParameterRepository {
    AppParameter save(AppParameter parameter);
    Optional<AppParameter> findByKey(AppParameterKey key);
    List<AppParameter> findAll(SortCriteria sorting);
    void deleteByKey(AppParameterKey key);
}
