package com.sistema_expedientes.rsa.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "data.to-encrypt")
public record DataToEncryptParameters(String path, String fileName, String fileExtension) {
}
