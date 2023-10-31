package com.sistema_expedientes.controllers;

import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.dto.request.LegajoRequestDTO;
import com.sistema_expedientes.services.interfaces.LegajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LegajoControlador {

    @Autowired
    private LegajoServicio servicio;

    @PostMapping("api/v1/legajos/crear")
    public ResponseEntity<Legajo> create(@RequestBody LegajoRequestDTO request) throws Exception {
        return ResponseEntity.ok(this.servicio.create(request));
    }

}
