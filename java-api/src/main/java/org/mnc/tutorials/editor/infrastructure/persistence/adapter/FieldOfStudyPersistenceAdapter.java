package org.mnc.tutorials.editor.infrastructure.persistence.adapter;

import jakarta.persistence.criteria.Predicate;
import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.mnc.tutorials.editor.infrastructure.persistence.entity.FieldOfStudyEntity;
import org.mnc.tutorials.editor.infrastructure.persistence.jpa.JpaFieldOfStudyRepository;
import org.mnc.tutorials.editor.infrastructure.persistence.mapper.FieldOfStudyMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FieldOfStudyPersistenceAdapter implements FieldOfStudyRepository {

    private final JpaFieldOfStudyRepository jpaFieldOfStudyRepository;
    private final FieldOfStudyMapper fieldOfStudyMapper;

    public FieldOfStudyPersistenceAdapter(JpaFieldOfStudyRepository jpaFieldOfStudyRepository, FieldOfStudyMapper fieldOfStudyMapper) {
        this.jpaFieldOfStudyRepository = jpaFieldOfStudyRepository;
        this.fieldOfStudyMapper = fieldOfStudyMapper;
    }

    @Override
    public FieldOfStudy save(FieldOfStudy fieldOfStudy) {
        FieldOfStudyEntity entity = jpaFieldOfStudyRepository.save(fieldOfStudyMapper.fromDomain(fieldOfStudy));
        return fieldOfStudyMapper.toDomain(entity);
    }

    @Override
    public Optional<FieldOfStudy> findById(UUID id) {
        return jpaFieldOfStudyRepository.findById(id)
                .map(fieldOfStudyMapper::toDomain);
    }

    @Override
    public Optional<FieldOfStudy> findByNameIgnoreCase(String name) {
        return jpaFieldOfStudyRepository.findByNameIgnoreCase(name)
                .map(fieldOfStudyMapper::toDomain);
    }

    @Override
    public DomainPage<FieldOfStudy> findByCriteria(FieldOfStudyCriteria criteria) {
        Specification<FieldOfStudyEntity> spec = withCriteria(criteria.getNameLike(), criteria.getDescriptionContain(), criteria.getApproved());
        var pageRequest = PageRequest.of(criteria.getPage(), criteria.getSize(), SortingUtils.getSort(criteria.getSorting()));
        Page<FieldOfStudyEntity> entityPage = jpaFieldOfStudyRepository.findAll(spec,pageRequest);

        return fieldOfStudyMapper.toDomainPage(entityPage);
    }

    @Override
    public void deleteById(UUID id) {
        jpaFieldOfStudyRepository.deleteById(id);
    }

    private static Specification<FieldOfStudyEntity> withCriteria(String name, String description, Boolean approved) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (description != null && !description.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
            }
            if (approved != null) {
                predicates.add(cb.equal(root.get("approved"),approved));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
