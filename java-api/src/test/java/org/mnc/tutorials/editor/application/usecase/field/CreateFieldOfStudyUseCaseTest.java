package org.mnc.tutorials.editor.application.usecase.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.utils.AlreadyExistsException;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
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
public class CreateFieldOfStudyUseCaseTest {

    @Mock
    private FieldOfStudyRepository repository;

    @InjectMocks
    private CreateFieldOfStudyUseCase useCase;

    private final UUID userId = UUID.randomUUID();

    @Test
    void execute_ShouldSave_WhenNameIsUnique() {
        // Arrange
        FieldOfStudyDto dto = new FieldOfStudyDto(null, "New Science", "Description", false);
        // Mock findByName (the method we added to the repo earlier) to return empty
        when(repository.findByNameIgnoreCase("New Science")).thenReturn(Optional.empty());
        when(repository.save(any(FieldOfStudy.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        FieldOfStudy result = useCase.execute(dto, userId);

        // Assert
        assertThat(result.getName()).isEqualTo("New Science");
        verify(repository).save(any(FieldOfStudy.class));
    }

    @Test
    void execute_ShouldThrow_WhenNameAlreadyExists() {
        // Arrange
        FieldOfStudyDto dto = new FieldOfStudyDto(null, "Existing", "Desc", false);
        FieldOfStudy existing = new FieldOfStudy(userId, "Existing", "Desc", true);
        when(repository.findByNameIgnoreCase("Existing")).thenReturn(Optional.of(existing));

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> useCase.execute(dto, userId));
        verify(repository, never()).save(any());
    }
}
