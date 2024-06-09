package com.nhnacademy.minidorray_gateway.config;

//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Task API").version("1.0").description("springdoc-openapi"));
//    }
//
//    @Bean
//    public GroupedOpenApi api() {
//        String[] paths = {"/comments/**", "/milestones/**", "/tags/**", "/tasks/**", "/projects/", "/", "/api/**", "/auth", "/profile", "/register"};
//        String[] packagesToScan = {"com.nhnacademy.minidorray_gateway"};
//        return GroupedOpenApi.builder()
//                .group("gateway-api")
//                .pathsToMatch(paths)
//                .packagesToScan(packagesToScan)
//                .build();
//    }
//}



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("springdoc-openapi")
                        .version("1.0")
                        .description("springdoc-openapi swagger-ui 화면입니다."));
    }

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/**"};
        String[] packagesToScan = {"com.nhnacademy.account_api"};
        return GroupedOpenApi.builder().group("springdoc-openapi")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }
}