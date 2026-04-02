package org.mnc.tutorials.editor.infrastructure.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "org.mnc.tutorials.editor.infrastructure.persistence.jpa"
})
@ComponentScan(basePackages = {
        "org.mnc.tutorials.editor"
})
@EntityScan({
        "org.mnc.tutorials.editor.infrastructure.persistence.entity"
})
public class ApplicationConfig {
}
