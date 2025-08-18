package com.radical.childmonitoring.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI (Swagger) documentation.
 * This class sets up the basic information for the API documentation.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates and configures the OpenAPI bean.
     * This bean provides the metadata for the API documentation,
     * such as title, version, description, terms of service, contact information, and license.
     *
     * @return A configured {@link OpenAPI} object.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("School Management API")
                        .version("1.0")
                        .description("API documentation for the School Management application")
                        .termsOfService("https://tmapurisa.github.io/tafara/")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Support Team")
                                .url("https://tmapurisa.github.io/tafara/")
                                .email("support@example.com"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}