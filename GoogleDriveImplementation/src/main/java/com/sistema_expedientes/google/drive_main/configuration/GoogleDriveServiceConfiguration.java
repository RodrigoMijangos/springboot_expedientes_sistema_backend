package com.sistema_expedientes.google_service.configuration;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class GoogleDriveServiceConfiguration {

    private final GooglePropertiesConfiguration propertiesConfiguration;

    public GoogleDriveServiceConfiguration(GooglePropertiesConfiguration propertiesConfiguration){
        this.propertiesConfiguration = propertiesConfiguration;
    }

    @Bean
    public HttpRequestInitializer httpRequestInitializer() throws IOException{
        final List<String> SCOPES =
                Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);

        final String t = FileUtils.readFileToString(new ClassPathResource(this.propertiesConfiguration.serviceAccountAuthenticationKey()).getFile());

        System.out.println(t);

        final InputStream in = new ByteArrayInputStream(t.getBytes());

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
