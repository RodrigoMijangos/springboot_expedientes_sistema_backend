package com.sistema_expedientes.controllers;

import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import com.sistema_expedientes.unidad_administrativa.dto.request.UnidadAdministrativaRequestDTO;
import com.sistema_expedientes.services.unidad_administrativa.UnidadAdministrativaServicio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UnidadAdministrativaControlador {

    private final UnidadAdministrativaServicio servicio;

    public UnidadAdministrativaControlador(UnidadAdministrativaServicio servicio){
        this.servicio = servicio;
    }

    @GetMapping("api/v1/unidades_administrativas/list")
    public ResponseEntity<List<UnidadAdministrativa>> index(){
        return ResponseEntity.ok(servicio.list());
    }

    @GetMapping("api/v1/unidades_administrativas/get/{clave}")
    public ResponseEntity<UnidadAdministrativa> get(@PathVariable @Size(max = 5) String clave) throws ResourceNotFoundException {
        return ResponseEntity.ok(servicio.get(clave));

    }

    @PostMapping("api/v1/unidades_administrativas/create")
    public ResponseEntity<UnidadAdministrativa> create(@Valid @RequestBody UnidadAdministrativaRequestDTO request) throws ResourceNotFoundException, IOException {
        return ResponseEntity.status(201).body(servicio.create(request));
    }

    @PutMapping("api/v1/unidades_administrativas/put/{clave}")
    public ResponseEntity<UnidadAdministrativa> put(@PathVariable @Size(max = 5) String clave, @Valid @RequestBody UnidadAdministrativaRequestDTO request) throws ResourceNotFoundException {
        return ResponseEntity.ok(servicio.put(clave, request));

    }

    @DeleteMapping("api/v1/unidades_administrativas/put/{clave}")
    public ResponseEntity<String> delete(@PathVariable @Size(max = 5) String clave) throws ResourceNotFoundException {
        this.servicio.delete(clave);
        return ResponseEntity.ok("El recurso ha sido eliminado");
    }

}
