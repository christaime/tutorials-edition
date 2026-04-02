package org.mnc.tutorials.editor.infrastructure.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.dto.PageResult;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = { LocalDateTime.class })
public interface MapStructFieldOfStudyDtoMapper extends FieldOfStudyDtoMapper {

    @Override
    @Mapping(target = "id", expression = "java(dto.id() != null ? UUID.fromString(dto.id()) : null)")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "approved", source = "dto.approved")
    // Set both createdBy and updatedBy from the method parameter
    @Mapping(target = "createdBy", source = "createdBy")
    @Mapping(target = "lastModificationBy", source = "createdBy")
    // Use an expression to call LocalDateTime.now() during mapping
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "lastModificationAt", expression = "java(LocalDateTime.now())")
    FieldOfStudy toDomain(UUID createdBy, FieldOfStudyDto dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "lastModificationBy", source = "modifiedBy")
    @Mapping(target = "lastModificationAt", expression = "java(LocalDateTime.now())")
    void updateDomain(@MappingTarget FieldOfStudy domain, FieldOfStudyDto dto, UUID modifiedBy);

    @Override
    FieldOfStudyDto toDto(FieldOfStudy domain);

    @Override
    default PageResult<FieldOfStudyDto> toPageResult(DomainPage<FieldOfStudy> domainPage) {
        if (domainPage == null) {
            return null;
        }
        List<FieldOfStudyDto> dtoList = domainPage.content()
                .stream()
                .map(this::toDto)
                .toList();

        return new PageResult<>(
                dtoList,
                domainPage.pageNumber(),
                domainPage.pageSize(),
                domainPage.totalElements(),
                domainPage.totalPages()
        );
    }
}
