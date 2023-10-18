package com.sistema_expedientes.controllers;

import com.sistema_expedientes.entities.Seccion;
import com.sistema_expedientes.entities.dto.request.SeccionRequestDTO;
import com.sistema_expedientes.services.SeccionServicio;
import com.sistema_expedientes.services.exceptions.SeccionNoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeccionControlador {

    @Autowired
    private SeccionServicio servicio;


    @GetMapping("api/v1/secciones/detalles/{clave}")
    public ResponseEntity<Seccion> get(@PathVariable String clave) throws SeccionNoEncontradaException {
        return ResponseEntity.ok(servicio.get(clave));
    }

    @GetMapping("api/v1/secciones/listar")
    public ResponseEntity<List<Seccion>> listAll(){
        List<Seccion> request = servicio.getAll();
        return request.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(request);
    }

    @PostMapping("api/v1/secciones/crear")
    public ResponseEntity<Seccion> create(@RequestBody SeccionRequestDTO request){
        return ResponseEntity.status(201).body(servicio.create(request));
    }

    @PutMapping("api/v1/secciones/detalles/{clave}/editar")
    public ResponseEntity<Seccion> put(@PathVariable String clave, @RequestBody SeccionRequestDTO request) throws SeccionNoEncontradaException {
        return ResponseEntity.ok(servicio.put(clave, request));
    }

}
