package org.mnc.tutorials.editor.infrastructure.persistence.adapter;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mnc.tutorials.editor.FlywayConfig;
import org.mnc.tutorials.editor.domain.model.tutorial.FieldOfStudy;
import org.mnc.tutorials.editor.domain.repository.FieldOfStudyCriteria;
import org.mnc.tutorials.editor.domain.repository.SortCriteria;
import org.mnc.tutorials.editor.infrastructure.mapping.MapStructFieldOfStudyDtoMapperImpl;
import org.mnc.tutorials.editor.infrastructure.persistence.jpa.JpaFieldOfStudyRepository;
import org.mnc.tutorials.editor.infrastructure.persistence.mapping.FieldOfStudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.TestDatabaseAutoConfiguration;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
@Import({
        FieldOfStudyPersistenceAdapter.class,
        TestMapperConfig.class,
        FlywayConfig.class
})
@Testcontainers
public class FieldOfStudyPersistenceAdapterIntegrationTest {

    @Container
    @ServiceConnection // This automatically configures the URL, username, and password
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:18-alpine")
            .withLogConsumer(new org.testcontainers.containers.output.Slf4jLogConsumer(org.slf4j.LoggerFactory.getLogger("postgres-container")))
            .withReuse(true);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FieldOfStudyPersistenceAdapter persistenceAdapter;

    @Autowired
    private JpaFieldOfStudyRepository jpaRepository;

    private final UUID userId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        jpaRepository.deleteAll();
    }

    @Test
    @DisplayName("Test if FieldOfStudyPersistenceAdapter bean is created successfully")
    void fieldOfStudyPersistenceAdapterBeanTest() {
        Assertions.assertNotNull(persistenceAdapter);
    }

    // --- SAVE TESTS ---
    @Test
    void save_Success() {
        FieldOfStudy domain = new FieldOfStudy(userId, "Computer Science", "CS Description", false);
        FieldOfStudy saved = persistenceAdapter.save(domain);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Computer Science");
    }

    @Test
    void save_Fail_DuplicateName() {
        persistenceAdapter.save(new FieldOfStudy(userId, "Java", "Desc", false));
        entityManager.flush();
        FieldOfStudy duplicate = new FieldOfStudy(userId, "Java", "Other", false);

        assertThrows(ConstraintViolationException.class, () ->{
            persistenceAdapter.save(duplicate);
            entityManager.flush();
        });
    }

    // --- FIND BY ID TESTS ---
    @Test
    void findById_Success() {
        FieldOfStudy saved = persistenceAdapter.save(new FieldOfStudy(userId, "Math", "Desc", true));
        Optional<FieldOfStudy> found = persistenceAdapter.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Math");
    }

    @Test
    void findById_NotFound() {
        assertThat(persistenceAdapter.findById(UUID.randomUUID())).isEmpty();
    }

    // --- SEARCH BY CRITERIA TESTS ---
    @Test
    void findByCriteria_FilterByName() {
        persistenceAdapter.save(new FieldOfStudy(userId, "Biology", "Bio", true));
        persistenceAdapter.save(new FieldOfStudy(userId, "Chemistry", "Chem", true));

        FieldOfStudyCriteria criteria = new FieldOfStudyCriteria("bio", null, true, new SortCriteria( SortCriteria.Direction.ASC,"name"), 0, 10);
        var result = persistenceAdapter.findByCriteria(criteria);

        assertThat(result.content()).hasSize(1);
        assertThat(result.content().get(0).getName()).isEqualTo("Biology");
    }

    @Test
    void findByCriteria_FilterByApproved() {
        persistenceAdapter.save(new FieldOfStudy(userId, "Approved", "Desc", true));
        persistenceAdapter.save(new FieldOfStudy(userId, "Pending", "Desc", false));

        FieldOfStudyCriteria criteria = new FieldOfStudyCriteria(null, null, false, new SortCriteria( SortCriteria.Direction.ASC,"name"), 0, 10);
        var result = persistenceAdapter.findByCriteria(criteria);

        assertThat(result.content()).hasSize(1);
        assertThat(result.content().get(0).getName()).isEqualTo("Pending");
    }

    @Test
    void findByCriteria_Sorting_Desc() {
        persistenceAdapter.save(new FieldOfStudy(userId, "A-Field", "Desc", true));
        persistenceAdapter.save(new FieldOfStudy(userId, "Z-Field", "Desc", true));

        FieldOfStudyCriteria criteria = new FieldOfStudyCriteria(null, null, null, new SortCriteria( SortCriteria.Direction.DESC,"name"), 0, 10);
        var result = persistenceAdapter.findByCriteria(criteria);

        assertThat(result.content().get(0).getName()).isEqualTo("Z-Field");
    }

    // --- DELETE TESTS ---
    @Test
    void delete_Success() {
        FieldOfStudy saved = persistenceAdapter.save(new FieldOfStudy(userId, "Physics", "Desc", true));
        persistenceAdapter.deleteById(saved.getId());

        assertThat(jpaRepository.findById(saved.getId())).isEmpty();
    }

    @Test
    void delete_NonExistent_DoesNotThrow() {
        // JPA deleteById typically doesn't throw if ID is missing in modern Spring Boot
        persistenceAdapter.deleteById(UUID.randomUUID());
    }
}

@TestConfiguration
class TestMapperConfig {
    @Bean
    public FieldOfStudyMapper fieldOfStudyMapper() {
        return Mappers.getMapper(FieldOfStudyMapper.class);
    }
}