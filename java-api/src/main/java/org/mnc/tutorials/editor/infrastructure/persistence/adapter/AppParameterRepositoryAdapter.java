package org.mnc.tutorials.editor.infrastructure.persistence.adapter;

import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.AppParameterRepository;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.AppParameterEntity;
import org.mnc.tutorials.editor.infrastructure.persistence.jpa.JpaAppParameterRepository;
import org.mnc.tutorials.editor.infrastructure.persistence.mapper.AppParameterMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AppParameterRepositoryAdapter implements AppParameterRepository {

    private final JpaAppParameterRepository jpaAppParameterRepository;
    private final AppParameterMapper appParameterMapper;

    public AppParameterRepositoryAdapter(JpaAppParameterRepository jpaAppParameterRepository, AppParameterMapper appParameterMapper) {
        this.jpaAppParameterRepository = jpaAppParameterRepository;
        this.appParameterMapper = appParameterMapper;
    }

    @Override
    public AppParameter save(AppParameter parameter) {
        AppParameterEntity param = jpaAppParameterRepository.save(appParameterMapper.toEntity(parameter));
        return appParameterMapper.toDomain(param);
    }

    @Override
    public Optional<AppParameter> findByKey(String key) {
        return jpaAppParameterRepository.findByKeyIgnoreCase(key)
                .map(appParameterMapper::toDomain);
    }

    @Override
    public List<AppParameter> findAll(SortCriteria sorting) {
        return jpaAppParameterRepository.findAll(SortingUtils.getSort(sorting)).stream()
                .map(appParameterMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteByKey(String key) {
        jpaAppParameterRepository.deleteByKeyIgnoreCase(key);
    }
}
