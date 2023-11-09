package com.sistema_expedientes.google_service.authentication;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleAuthenticationService {

    private static final String APPLICATION_NAME = "aplicacion de prueba";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKEN_DIRECTORY_PATH = "/tokens";
    private static final List<String> SCOPES =
            Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);
    private static final String TOKEN_AUTH_PATH = "/expedientesdocumentosproyecto-e1bdd723c4bc.json";

    private static HttpRequestInitializer getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        InputStream in = GoogleAuthenticationService.class.getResourceAsStream(TOKEN_AUTH_PATH);
        if(in == null)
            throw new FileNotFoundException("RESOURCE NOT FOUND: " + TOKEN_AUTH_PATH);
        final GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(SCOPES);
        return new HttpCredentialsAdapter(credentials);

    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        FileList result = service.files().list()
                .setPageSize(5)
                .setFields("nextPageToken, files(id, name, fileExtension, mimeType)")
                .execute();
        List<File> files = result.getFiles();
        if(files == null || files.isEmpty())
            System.out.println("No archivos jaja");
        else{
            System.out.println("Files: ");
            for(File file : files)
                System.out.printf("%s (%s), .%s, %s\n", file.getName(), file.getId(), file.getFileExtension(), file.getMimeType());
        }

    }

}
