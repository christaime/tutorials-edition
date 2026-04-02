package org.mnc.tutorials.editor.infrastructure.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

import java.util.Map;

@Configuration
@OpenAPIDefinition()
public class SwaggerConfig {

    @Bean(name = "swaggerAPI")
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tutorial Editor API")
                        .version("1.0.0")
                        .description("Rest Api de gestion d'édition de tutoriels")
                        .contact(new Contact().name("Tutorial Editor").email("support@gmail.com"))
                        .license(new License().name("Proprietary"))
                )
                .components(new Components()
                        .addSecuritySchemes("keycloak", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("Keycloak Authentication")
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl("http://localhost:8080/realms/tuto-editor/protocol/openid-connect/auth")
                                                .tokenUrl("http://localhost:8080/realms/tuto-editor/protocol/openid-connect/token")
                                                // Note: Some versions of Swagger UI use extensions for logout
                                                .extensions(Map.of("x-logout-url", "http://localhost:8080/realms/tuto-editor/protocol/openid-connect/logout"))
                                                .scopes(new Scopes().addString("openid", "OpenID Connect scope"))
                                        )
                                )
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("keycloak"));
    }

}