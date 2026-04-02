package org.mnc.tutorials.editor.application.usecase.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.mnc.tutorials.editor.infrastructure.mapping.MapStructFieldOfStudyDtoMapperImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateFieldOfStudyUseCaseTest {

    @Mock
    private FieldOfStudyRepository repository;

    @Spy
    private FieldOfStudyDtoMapper mapper = new MapStructFieldOfStudyDtoMapperImpl();

    @InjectMocks
    private UpdateFieldOfStudyUseCase useCase;

    @Test
    void execute_ShouldUpdate_WhenNotApproved() {
        // Arrange
        UUID fieldId = UUID.randomUUID();
        FieldOfStudy existing = new FieldOfStudy(fieldId, "Old Name", "Old Desc", false, UUID.randomUUID());
        FieldOfStudyDto updateDto = new FieldOfStudyDto(fieldId.toString(), "New Name", "New Desc", false);

        given(repository.findById(fieldId)).willReturn(Optional.of(existing));
        when(repository.save(any(FieldOfStudy.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        FieldOfStudyDto result = useCase.execute(existing.getId(), updateDto, UUID.randomUUID());

        // Assert
        assertThat(result.id()).isEqualTo(fieldId.toString());
        assertThat(result.name()).isEqualTo("New Name");
        assertThat(result.description()).isEqualTo("New Desc");
        assertThat(result.approved()).isEqualTo(false);
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
