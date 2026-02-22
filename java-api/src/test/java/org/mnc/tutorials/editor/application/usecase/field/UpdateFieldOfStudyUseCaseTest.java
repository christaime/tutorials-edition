package org.mnc.tutorials.editor.application.usecase.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.mnc.tutorials.editor.infrastructure.persistence.mapper.FieldOfStudyMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateFieldOfStudyUseCaseTest {

    @Mock
    private FieldOfStudyRepository repository;

    @Mock
    private FieldOfStudyDtoMapper mapper;

    @InjectMocks
    private UpdateFieldOfStudyUseCase useCase;

    @Test
    void execute_ShouldUpdate_WhenNotApproved() {
        // Arrange
        FieldOfStudy existing = new FieldOfStudy(UUID.randomUUID(), "Old Name", "Old Desc", false, UUID.randomUUID());
        FieldOfStudyDto updateDto = new FieldOfStudyDto(existing.getId().toString(), "New Name", "New Desc", false);

        when(repository.findById(existing.getId())).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));
        doAnswer(invocation -> {
            FieldOfStudy domain = invocation.getArgument(0);
            FieldOfStudyDto sourceDto = invocation.getArgument(1);
            domain.setName(sourceDto.name());
            domain.setDescription(sourceDto.description());
            return null; // void methods return null in doAnswer
        }).when(mapper).updateDomain(any(), any(), any());

        // Act
        FieldOfStudy result = useCase.execute(existing.getId(), updateDto, UUID.randomUUID());

        // Assert
        assertThat(result.getName()).isEqualTo("New Name");
    }

    @Test
    void execute_ShouldThrow_WhenAlreadyApproved() {
        // Arrange
        UUID id = UUID.randomUUID();
        // Domain model 'isApproved' is true
        FieldOfStudy existing = new FieldOfStudy(id, "Old Name", "Old Desc", true, UUID.randomUUID());
        FieldOfStudyDto updateDto = new FieldOfStudyDto(id.toString(), "New Name", "New Desc", true);

        when(repository.findById(id)).thenReturn(Optional.of(existing));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> useCase.execute(id, updateDto, UUID.randomUUID()));
    }
}
