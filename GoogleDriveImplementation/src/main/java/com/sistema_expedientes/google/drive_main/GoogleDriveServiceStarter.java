package com.sistema_expedientes.google_service;

import com.sistema_expedientes.google_service.configuration.GoogleDrivePropertiesConfiguration;
import com.sistema_expedientes.google_service.configuration.GooglePropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        GooglePropertiesConfiguration.class,
        GoogleDrivePropertiesConfiguration.class
})
public class GoogleDriveServiceStarter {

    public static void main(String[] args) {
        SpringApplication.run(GoogleDriveServiceStarter.class, args);
    }

}
