package com.sistema_expedientes.google.drive_main;

import com.sistema_expedientes.google.drive_main.service.GoogleDriveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    private final GoogleDriveService service;

    public TestController(GoogleDriveService service) {
        this.service = service;
    }

    @GetMapping("test")
    public void test() throws IOException {
        service.getResults();
    }

    @GetMapping("test2")
    public void test2()throws Exception{
        service.delete();
    }

}
