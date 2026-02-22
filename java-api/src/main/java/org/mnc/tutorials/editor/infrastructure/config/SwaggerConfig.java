package org.mnc.tutorials.editor.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerAPI() {
        return new OpenAPI()
        .info(new Info()
            .title("Tutorial Editor API")
            .version("1.0.0")
            .description("Rest Api de ")
            .contact(new Contact().name("Tutorial Editor").email("support@baotec.com"))
            .license(new License().name("Proprietary"))
        )
        .components(new Components());
    }
}