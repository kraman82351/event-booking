package com.harshchauhan.irctc_core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.harshchauhan.irctc_core.common.constants.AppConstants;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("IRCTC")
                        .description("API documentation for the application")
                        .version("1.0.0")
                        .termsOfService(null)
                        .contact(null)
                        .license(null))
                .addSecurityItem(new SecurityRequirement().addList(AppConstants.SECURITY_SCHEME_NAME)) // <- Apply globally
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(AppConstants.SECURITY_SCHEME_NAME, new SecurityScheme()
                                .name(AppConstants.SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

}
