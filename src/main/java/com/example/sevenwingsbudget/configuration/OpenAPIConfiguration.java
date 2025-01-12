package com.example.sevenwingsbudget.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Value("${app.url}")
    private String serverUrl;

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerUrl;


    @Bean
    public OpenAPI defineOpenApi() {
        System.out.printf("Swagger available at %s%s\n", serverUrl, swaggerUrl);
        return new OpenAPI().info(new Info()
                        .title("Construction Dashboard")
                        .version("1.0.0")
                        .description("Backend API"))
                .servers(List.of(new Server()
                        .url(serverUrl)
                        .description("localhost")));
    }
}
