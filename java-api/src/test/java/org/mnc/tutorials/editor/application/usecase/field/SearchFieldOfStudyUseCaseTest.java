package org.mnc.tutorials.editor.application.usecase.field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mnc.tutorials.editor.domain.model.DomainPage;
import org.mnc.tutorials.editor.domain.model.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyRepository;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchFieldOfStudyUseCaseTest {

    @Mock
    private FieldOfStudyRepository repository;

    @InjectMocks
    private SearchFieldOfStudyUseCase useCase;

    @Test
    void execute_ShouldReturnPagedResults_WhenCriteriaProvided() {
        // Arrange
        SortCriteria sorting = new SortCriteria(SortCriteria.Direction.ASC, "name");
        FieldOfStudyCriteria criteria = new FieldOfStudyCriteria("Java", "Programming", true, sorting,0, 10);

        // Mocking a DomainPage response
        FieldOfStudy mockField = new FieldOfStudy(UUID.randomUUID(), "Java", "Desc", true);
        DomainPage<FieldOfStudy> expectedPage = new DomainPage<>(
                List.of(mockField), 0, 10, 1L, 1
        );

        when(repository.findByCriteria(criteria)).thenReturn(expectedPage);

        // Act
        DomainPage<FieldOfStudy> result = useCase.execute(criteria);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.content()).hasSize(1);
        assertThat(result.content().get(0).getName()).isEqualTo("Java");

        // Verify the repository was called exactly once with our criteria
        verify(repository, times(1)).findByCriteria(criteria);
    }

    @Test
    void execute_ShouldReturnEmptyPage_WhenNoResultsFound() {
        // Arrange
        FieldOfStudyCriteria criteria = new FieldOfStudyCriteria("NonExistent", null, null, null,0, 10);
        DomainPage<FieldOfStudy> emptyPage = new DomainPage<>(List.of(), 0, 10, 0L, 0);

        when(repository.findByCriteria(any())).thenReturn(emptyPage);

        // Act
        DomainPage<FieldOfStudy> result = useCase.execute(criteria);

        // Assert
        assertThat(result.content()).isEmpty();
        assertThat(result.totalElements()).isZero();
    }
}