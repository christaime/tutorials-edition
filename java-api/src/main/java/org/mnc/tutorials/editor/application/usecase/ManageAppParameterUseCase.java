package org.mnc.tutorials.editor.application.usecase;

import org.mnc.tutorials.editor.application.mapper.AppParameterDtoMapper;
import org.mnc.tutorials.editor.application.utils.NotFoundException;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.domain.repository.AppParameterRepository;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManageAppParameterUseCase {

    private final AppParameterRepository repository;
    private final AppParameterDtoMapper mapper;

    public ManageAppParameterUseCase(AppParameterRepository repository, AppParameterDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void setParameter(AppParameter parameter, UUID adminId) {
        AppParameter param = repository.findByKey(parameter.getKey())
        .map(p -> {
            mapper.update(p,parameter);
            return p;
        })
        .orElseGet(() -> {
            parameter.setId(UUID.randomUUID());
            parameter.setCreatedAt(LocalDateTime.now());
            parameter.setCreatedBy(adminId);
            return parameter;
        });

        param.setLastModificationBy(adminId);
        param.setLastModificationAt(LocalDateTime.now());

        repository.save(param);
    }

    public Optional<AppParameter> getParameter(String key) {
        return repository.findByKey(key);
    }

    public List<AppParameter> getParameterList(SortCriteria sorting) {
        return repository.findAll(sorting);
    }

    public void deleteParameter(String key) {
        repository.findByKey(key).orElseThrow(() -> new NotFoundException("Parameter not found"));
        repository.deleteByKey(key);
    }
}
