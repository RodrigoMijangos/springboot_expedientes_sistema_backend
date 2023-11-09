package com.sistema_expedientes.google_service.configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class DriveServiceConfiguration {

    @Bean
    public HttpRequestInitializer httpRequestInitializer() throws IOException{
        final String TOKEN_AUTH_TOKEN = "/expedientesdocumentosproyecto-e1bdd723c4bc.json";
        final List<String> SCOPES =
                Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);

        final InputStream in = DriveServiceConfiguration.class.getResourceAsStream(TOKEN_AUTH_TOKEN);

        if(in == null)
            throw new IOException("RESOURCE NOT FOUND: " + TOKEN_AUTH_TOKEN);
        else
            return new HttpCredentialsAdapter(GoogleCredentials.fromStream(in)
                    .createScoped(SCOPES));
    }

    @Bean
    public Drive GoogleCloudDriveService(HttpRequestInitializer httpRequestInitializer) throws GeneralSecurityException, IOException {
        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                httpRequestInitializer)
                .setApplicationName("Test Drive Application")
                .build();
    }

}
