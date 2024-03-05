package com.sistema_expedientes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Controlador de Usuario")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Operation(summary = "Retorna el nivel de acceso de usuario")
    @GetMapping
    public String hello(){
        return "User acces level";
    }
}
