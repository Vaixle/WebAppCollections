package com.petushkov.webappcollections.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Collections;

/**
 * Configuration for swagger
 */
@Configuration
public class SpringFoxConfig {

    /**
     * Customize swagger properties
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) //select all requests
                .paths(PathSelectors.any()) //select all paths
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Collections API",
                "API documentation for course project.",
                "API 1.0",
                "Terms of service",
                new Contact("Petr Petushkov", "www.inftix.com", "petushkovpeter@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
