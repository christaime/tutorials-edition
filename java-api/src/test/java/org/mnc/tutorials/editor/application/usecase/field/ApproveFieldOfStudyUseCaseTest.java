package org.mnc.tutorials.editor.application.usecase.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mnc.tutorials.editor.application.dto.FieldOfStudyDto;
import org.mnc.tutorials.editor.application.mapper.FieldOfStudyDtoMapper;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;
import org.mnc.tutorials.editor.infrastructure.mapping.MapStructFieldOfStudyDtoMapperImpl;
import org.mnc.tutorials.editor.infrastructure.persistence.mapping.FieldOfStudyMapper;
import org.mnc.tutorials.editor.infrastructure.persistence.mapping.FieldOfStudyMapperImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApproveFieldOfStudyUseCaseTest {

    @Mock
    private FieldOfStudyRepository repository;

    @InjectMocks
    private ApproveFieldOfStudyUseCase useCase;

    @Spy
    private FieldOfStudyDtoMapper mapper = new MapStructFieldOfStudyDtoMapperImpl();

    @Test
    void execute_ShouldSetApprovedToTrue() {
        // Arrange
        UUID id = UUID.randomUUID();
        FieldOfStudy pending = new FieldOfStudy(UUID.randomUUID(), "Physics", "Desc", false);
        given(repository.findById(id)).willReturn(Optional.of(pending));
        given(repository.save(any(FieldOfStudy.class))).willAnswer(invocation -> invocation.getArgument(0));

        // Act
        useCase.execute(id, UUID.randomUUID());

        // Assert
        assertThat(pending.isApproved()).isTrue();

        verify(repository).save(pending);
        verify(mapper).toDto(any());
    }
}
