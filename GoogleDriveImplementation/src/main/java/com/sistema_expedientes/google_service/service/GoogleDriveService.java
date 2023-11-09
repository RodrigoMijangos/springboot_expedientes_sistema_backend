package com.sistema_expedientes.google_service.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GoogleDriveService {

    private final Drive googleDriveService;

    public GoogleDriveService(Drive googleDriveService) {
        this.googleDriveService = googleDriveService;
    }

    public void getResults() throws IOException {
        FileList results = this.googleDriveService.files().list()
                .setPageSize(5)
                .setFields("nextPageToken, files(id, name, fileExtension, mimeType)")
                .execute();

        List<File> files = results.getFiles();
        if(files == null || files.isEmpty())
            System.out.println("No archivos jaja");
        else{
            System.out.println("Files: ");
            for(File file : files)
                System.out.printf("%s (%s), .%s, %s\n", file.getName(), file.getId(), file.getFileExtension(), file.getMimeType());
        }
    }

}
