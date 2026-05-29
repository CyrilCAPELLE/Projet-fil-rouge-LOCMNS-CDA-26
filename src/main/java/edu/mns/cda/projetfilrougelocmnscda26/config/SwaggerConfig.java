package edu.mns.cda.projetfilrougelocmnscda26.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Authentification Bearer"))
                .components(new Components()
                        .addSecuritySchemes("Authentification Bearer", schemaJwt()))
                .info(new Info()
                        .title("API LOC MNS")
                        .description("API de gestion du parc de materiel empruntable")
                        .version("1.0"));
    }

    private SecurityScheme schemaJwt() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}