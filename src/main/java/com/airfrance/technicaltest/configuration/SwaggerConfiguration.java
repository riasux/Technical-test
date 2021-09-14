package com.airfrance.technicaltest.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private final BuildProperties buildProperties;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public OpenAPI cockpitOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Technique test API")
                        .description("Documentation for the technical test API")
                        .version(buildProperties.getVersion()))
                .addServersItem(new Server().url(contextPath));
    }
}
