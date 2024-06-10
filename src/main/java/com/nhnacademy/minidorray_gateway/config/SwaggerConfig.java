package com.nhnacademy.minidorray_gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Task API").version("1.0").description("springdoc-openapi"));
    }

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/comments/**", "/milestones/**", "/tags/**", "/tasks/**", "/projects/", "/", "/api/**", "/auth", "/profile", "/register"};
        String[] packagesToScan = {"com.nhnacademy.minidorray_gateway"};
        return GroupedOpenApi.builder()
                .group("gateway-api")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }
}


