package com.sistema_expedientes;

import com.sistema_expedientes.google.drive_main.configuration.properties.GoogleDriveApplicationProperties;
import com.sistema_expedientes.google.drive_main.configuration.properties.GoogleDriveConfigurationServiceProperties;
import com.sistema_expedientes.google.drive_main.configuration.properties.GoogleDriveConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        GoogleDriveConfigurationProperties.class,
        GoogleDriveApplicationProperties.class,
        GoogleDriveConfigurationServiceProperties.class
})
public class EndpointExpediente{

    public static void main(String[] args) {
        SpringApplication.run(EndpointExpediente.class, args);
    }
}
