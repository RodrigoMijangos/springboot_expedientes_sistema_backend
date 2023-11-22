package com.sistema_expedientes.rsa.controller;

import com.sistema_expedientes.rsa.configuration.DataToEncryptParameters;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class RSADataEncryptionController {

    // From Repository Root
    // GoogleDriveImplementation/src/main/resources

    //Absolute Path
    // C:\Users\rodri\Documents_LOCAL\GithubProjects\JAVA\expedientes_contenedor\GoogleDriveImplementation\src\main\resources
    private final DataToEncryptParameters dataToEncryptParameters;


    public RSADataEncryptionController(DataToEncryptParameters dataToEncryptParameters) {
        this.dataToEncryptParameters = dataToEncryptParameters;
    }

    @GetMapping(value = "api/v1/util/encrypt_data", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<String> encryptLocalData() throws IOException {

        File file = new ClassPathResource(dataToEncryptParameters.path() + dataToEncryptParameters.fileName()).getFile();

        FileInputStream in = new FileInputStream(
                file
        );

        Path cosa = Files.write(Paths.get("C:/User/rodri/Documents_LOCAL/GithubProjects/JAVA/expedientes_contenedor/GoogleDriveImplementation/src/main/resources"), in.readAllBytes());

        return ResponseEntity.ok(cosa.toString());

    }

}
