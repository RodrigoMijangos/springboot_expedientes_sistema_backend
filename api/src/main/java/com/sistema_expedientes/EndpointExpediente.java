package com.sistema_expedientes;

import com.sistema_expedientes.google.drive_main.configuration.properties.GoogleDrivePropertiesConfiguration;
import com.sistema_expedientes.google.drive_main.configuration.properties.GooglePropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        GooglePropertiesConfiguration.class,
        GoogleDrivePropertiesConfiguration.class
})
public class EndpointExpediente{

    public static void main(String[] args) {
        SpringApplication.run(EndpointExpediente.class, args);
    }
}
