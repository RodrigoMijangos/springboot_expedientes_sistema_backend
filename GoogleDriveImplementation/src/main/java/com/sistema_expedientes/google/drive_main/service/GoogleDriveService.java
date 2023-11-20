package com.sistema_expedientes.google.drive_main.service;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.DriveList;
import com.google.api.services.drive.model.File;
import com.sistema_expedientes.google.drive_main.configuration.properties.GoogleDrivePropertiesConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
public class GoogleDriveService {

    private final Drive googleDriveService;
    private final GoogleDrivePropertiesConfiguration propertiesConfiguration;

    private final String folderMimeType = "application/vnd.google-apps.folder";

    public GoogleDriveService(Drive googleDriveService, GoogleDrivePropertiesConfiguration propertiesConfiguration) {
        this.googleDriveService = googleDriveService;
        this.propertiesConfiguration = propertiesConfiguration;
    }

    public void getResults() throws IOException {
        DriveList results = this.googleDriveService.drives().list()
                .setPageSize(5)
                .execute();

        List<com.google.api.services.drive.model.Drive> files = results.getDrives();
        if(files == null || files.isEmpty())
            System.out.println("No archivos jaja");
        else{
            System.out.println("Files: ");
            for(com.google.api.services.drive.model.Drive file : files)
                System.out.printf("%s (%s), .%s, %s\n", file.getName(), file.getId(), file.getKind(), file.getCreatedTime());
        }
    }

    public Map<String , String> saveFile(MultipartFile sourceFile) throws IOException {

        File uploadedFile = executeGoogleDriveRequest(sourceFile);

        HashMap<String, String> retorno = new HashMap<>();

        retorno.put("id", uploadedFile.getId());
        retorno.put("shareViewLink", uploadedFile.getWebViewLink());

        System.out.println(retorno.values());

        return retorno;
    }

    public void delete(){
        List<String> files = List.of(new String[]{
                "1AZYTnEBbr2QvdqcShdDN03jgDL6oroin",
                "14XTpkSgZuhP-4_Tg69tbSbFflf9sbAHj"
        });

        files.forEach(id -> {
            try {
                System.err.println(googleDriveService.files().delete(id).execute());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private File setMetadata(MultipartFile sourceFile){
        return new File()
                .setName(sourceFile.getOriginalFilename())
                .setMimeType(sourceFile.getContentType())
                .setParents(
                        Collections.singletonList(this.propertiesConfiguration.parentFolderId())
                );
    }

    private File executeGoogleDriveRequest(MultipartFile sourceFile) throws IOException {
        return googleDriveService.files()
                .create(setMetadata(sourceFile),
                        new InputStreamContent(
                                sourceFile.getContentType(),
                                new ByteArrayInputStream(sourceFile.getBytes())
                        ))
                .setUploadType("multipart")
                .setFields("id, webViewLink, permissions")
                .execute();
    }

}
