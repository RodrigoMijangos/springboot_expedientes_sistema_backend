package com.sistema_expedientes.rsa.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "encrypt.rsa")
public record RSAServiceProperties(@NotNull String privateKey, @NotNull String publicKey) {
}
