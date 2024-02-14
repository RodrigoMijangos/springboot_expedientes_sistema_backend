package com.sistema_expedientes.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gestion de Expedientes API - INFOTEC",
                version = "1.0.0",
                description = "Api para la gestión de expedientes de manera semiautomatizada"
        )
)
public class OpenApiConfig {
}
