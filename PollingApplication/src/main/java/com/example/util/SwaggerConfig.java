package com.example.util;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI pollingAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Polling Application API")
                        .version("1.0.0")
                        .description("API documentation for the Polling Application")
                        .contact(new Contact()
                                .name("Merin Joy")
                                .email("your-email@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
