package org.mnc.tutorials.editor.application.usecase.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApproveFieldOfStudyUseCaseTest {

    @Mock private FieldOfStudyRepository repository;
    @InjectMocks private ApproveFieldOfStudyUseCase useCase;

    @Test
    void execute_ShouldSetApprovedToTrue() {
        // Arrange
        UUID id = UUID.randomUUID();
        FieldOfStudy pending = new FieldOfStudy(UUID.randomUUID(), "Physics", "Desc", false);
        when(repository.findById(id)).thenReturn(Optional.of(pending));

        // Act
        useCase.execute(id, UUID.randomUUID());

        // Assert
        assertThat(pending.isApproved()).isTrue();
        verify(repository).save(pending);
    }
}
