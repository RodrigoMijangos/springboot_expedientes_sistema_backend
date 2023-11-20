package com.sistema_expedientes.google.drive_main.configuration.properties;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google")
public record GooglePropertiesConfiguration(@NotNull String serviceAccountAuthenticationKey) {
}
