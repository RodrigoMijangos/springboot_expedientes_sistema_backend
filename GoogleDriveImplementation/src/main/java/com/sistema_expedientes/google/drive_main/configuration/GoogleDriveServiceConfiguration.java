package com.sistema_expedientes.google.drive_main.configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.sistema_expedientes.google.drive_main.configuration.properties.GoogleDriveApplicationProperties;
import com.sistema_expedientes.google.drive_main.configuration.properties.GooglePropertiesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class GoogleDriveServiceConfiguration {

    private final GooglePropertiesConfiguration googlePropertiesConfiguration;
    private final GoogleDriveApplicationProperties googleDrivePropertiesConfiguration;

    public GoogleDriveServiceConfiguration(GooglePropertiesConfiguration googlePropertiesConfiguration, GoogleDriveApplicationProperties googleDriveApplicationProperties){
        this.googlePropertiesConfiguration = googlePropertiesConfiguration;
        this.googleDrivePropertiesConfiguration = googleDriveApplicationProperties;
    }

    @Bean
    public HttpRequestInitializer httpRequestInitializer() throws IOException{
        final List<String> SCOPES =
                Collections.singletonList(DriveScopes.DRIVE);

        final InputStream in = new FileInputStream(
                new ClassPathResource(
                        this.googlePropertiesConfiguration.serviceAccountAuthenticationKey())
                        .getFile()
        );

        return new HttpCredentialsAdapter(GoogleCredentials.fromStream(in)
                .createScoped(SCOPES));
    }

    @Bean
    public Drive GoogleCloudDriveService(HttpRequestInitializer httpRequestInitializer) throws GeneralSecurityException, IOException {
        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                httpRequestInitializer)
                .setApplicationName(googleDrivePropertiesConfiguration.ApplicationName())
                .build();
    }

}
