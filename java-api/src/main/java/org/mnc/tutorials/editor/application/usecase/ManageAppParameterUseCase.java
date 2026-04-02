package org.mnc.tutorials.editor.application.usecase;

import org.mnc.tutorials.editor.application.dto.AppParameterDto;
import org.mnc.tutorials.editor.application.mapper.AppParameterDtoMapper;
import org.mnc.tutorials.editor.application.utils.NotFoundException;
import org.mnc.tutorials.editor.domain.model.admin.AppParameter;
import org.mnc.tutorials.editor.domain.model.admin.AppParameterKey;
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

    public AppParameterDto setParameter(AppParameterDto parameter, UUID adminId) {
        AppParameter param = repository.findByKey(parameter.key())
        .map(p -> {
            mapper.update(p,parameter);
            return p;
        })
        .orElseGet(() -> {
            var domainParam = mapper.toDomain(parameter);
            domainParam.setId(UUID.randomUUID());
            domainParam.setCreatedAt(LocalDateTime.now());
            domainParam.setCreatedBy(adminId);
            return domainParam;
        });

        param.setLastModificationBy(adminId);
        param.setLastModificationAt(LocalDateTime.now());

        param = repository.save(param);
        return mapper.toDto(param);
    }

    public Optional<AppParameterDto> getParameter(AppParameterKey  key) {
        return repository.findByKey(key).map(mapper::toDto);
    }

    public List<AppParameterDto> getParameterList(SortCriteria sorting) {
        return repository.findAll(sorting).stream().map(mapper::toDto).toList();
    }

    public void deleteParameter(AppParameterKey key) {
        repository.findByKey(key).orElseThrow(() -> new NotFoundException("Parameter not found"));
        repository.deleteByKey(key);
    }
}
