package com.kr.tooit.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Swagger springdoc-ui 구성 파일
 */

@Configuration
public class SwaggerConfig {

    private static final String REFERENCE = "Bearer 토큰 값";

    @Bean
    public OpenAPI openAPI(@Value("${spring.profiles.active}") String active) {
        Info info = new Info()
                .title("Tooit API Document")
                .version("v0.1")
                .description("Tooit 프로젝트 API 명세서");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement schemaRequirement = new SecurityRequirement().addList("bearerAuth");


        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(schemaRequirement))
                .info(info);
    }
}
