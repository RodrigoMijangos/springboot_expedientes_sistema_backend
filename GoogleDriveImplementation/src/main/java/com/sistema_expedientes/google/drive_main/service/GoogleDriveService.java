package com.sistema_expedientes.google.drive_main.service;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.DriveList;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.sistema_expedientes.google.drive_main.configuration.properties.GoogleDriveConfigurationServiceProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
public class GoogleDriveService {

    private final Drive googleDriveService;
    private final GoogleDriveConfigurationServiceProperties googleDriveApplicationProperties;
    private final String folderMimeType;

    public GoogleDriveService(Drive googleDriveService, GoogleDriveConfigurationServiceProperties googleDriveApplicationProperties) {
        this.googleDriveService = googleDriveService;
        this.googleDriveApplicationProperties = googleDriveApplicationProperties;
        this.folderMimeType = "application/vnd.google-apps.folder";
    }

    public void getResults() throws IOException {
        DriveList results = this.googleDriveService.drives().list()
                .setPageSize(5)
                .execute();

        List<com.google.api.services.drive.model.Drive> files = results.getDrives();
        if (files == null || files.isEmpty())
            System.out.println("No archivos jaja");
        else {
            System.out.println("Files: ");
            for (com.google.api.services.drive.model.Drive file : files)
                System.out.printf("%s (%s), .%s, %s\n", file.getName(), file.getId(), file.getKind(), file.getCreatedTime());
        }
    }

    public Map<String, String> saveFile(MultipartFile sourceFile) throws IOException {

        File uploadedFile = executeGoogleDriveSaveFileRequest(sourceFile);

        HashMap<String, String> retorno = new HashMap<>();

        retorno.put("id", uploadedFile.getId());
        retorno.put("shareViewLink", uploadedFile.getWebViewLink());

        return retorno;
    }

    public String createFolder(String folderName, String folderParentId) throws Exception {
        File uploadedFile = executeGoogleDriveSaveFolderRequest(folderName, folderParentId);
        setAnyoneReaderPermission(uploadedFile);
        return uploadedFile.getId();
    }

    public String getRootFolderId(){
        return this.googleDriveApplicationProperties.rootFolderId();
    }

    public boolean deleteFileFromId(String id) throws IOException {
        this.googleDriveService.files().delete(id).execute();
        return true;
    }

    private File setFolderMetadata(String folderName, String folderParentId){
        return new File().setName(folderName)
                .setMimeType(this.folderMimeType)
                .setParents(Collections.singletonList(folderParentId));
    }

    private File executeGoogleDriveSaveFolderRequest(String folderName, String folderParentId) throws IOException {
        return googleDriveService.files().create(setFolderMetadata(folderName, folderParentId))
                .setFields("id")
                .execute();
    }

    private File setFileMetadata(MultipartFile sourceFile){
        return new File()
                .setName(sourceFile.getOriginalFilename())
                .setMimeType(sourceFile.getContentType())
                .setParents(
                        Collections.singletonList(this.googleDriveApplicationProperties.rootFolderId())
                );
    }

    private File executeGoogleDriveSaveFileRequest(MultipartFile sourceFile) throws IOException {
        return googleDriveService.files()
                .create(setFileMetadata(sourceFile),
                        new InputStreamContent(
                                sourceFile.getContentType(),
                                new ByteArrayInputStream(sourceFile.getBytes())
                        ))
                .setUploadType("multipart")
                .setFields("id, webViewLink, permissions")
                .execute();
    }

    private void setAnyoneReaderPermission(File uploadedFile) throws Exception{

        this.googleDriveService.permissions()
                .create(
                        uploadedFile.getId(),
                        new Permission()
                                .setRole("reader")
                                .setType("anyone")
                )
                .execute();
    }

}
