package org.mnc.tutorials.editor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "server.port=8082", // Use a that port for testing
        }
)
@Testcontainers
class ApplicationTests {

    @Container
    @ServiceConnection // This automatically configures the URL, username, and password
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:18-alpine")
            .withLogConsumer(new org.testcontainers.containers.output.Slf4jLogConsumer(org.slf4j.LoggerFactory.getLogger("postgres-container")))
            .withReuse(true);

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Test if the application context loads successfully")
    void contextLoads() {
        Assertions.assertNotNull(applicationContext,"Application context should not be null");
        Assertions.assertTrue(applicationContext.containsBean("swaggerAPI")," No swaggerAPI bean found");
        Assertions.assertTrue(applicationContext.containsBean("entityManagerFactory")," No entityManagerFactory bean found");
    }


}
