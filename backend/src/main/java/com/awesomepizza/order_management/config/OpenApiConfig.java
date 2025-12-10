package com.awesomepizza.order_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Francesco Sasso
 * @version 1.0
 * @since 2025
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI awesomePizzaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Awesome Pizza - Order Management API")
                        .description("API REST per la gestione degli ordini della pizzeria Awesome Pizza. " +
                                "Permette ai clienti di creare ordini e ai pizzaioli di gestire la coda.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Francesco")
                                .email("sasso.francescosasso00@gmail.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server")
                ))
                .tags(List.of(
                        new Tag()
                                .name("Orders")
                                .description("Endpoint per la gestione degli ordini da parte dei clienti"),
                        new Tag()
                                .name("Pizzaiolo")
                                .description("Endpoint per la gestione della coda ordini da parte del pizzaiolo")));
    }
}
