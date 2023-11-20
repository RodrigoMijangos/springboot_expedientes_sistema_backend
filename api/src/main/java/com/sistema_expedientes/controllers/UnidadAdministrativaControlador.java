package com.sistema_expedientes.controllers;

import com.sistema_expedientes.entities.UnidadAdministrativa;
import com.sistema_expedientes.entities.dto.request.UnidadAdministrativaRequestDTO;
import com.sistema_expedientes.services.UnidadAdministrativaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UnidadAdministrativaControlador {

    @Autowired
    private UnidadAdministrativaServicio servicio;

    @GetMapping("api/v1/unidades_administrativas/list")
    public ResponseEntity<List<UnidadAdministrativa>> index(){
        return ResponseEntity.ok(servicio.list());
    }

    @GetMapping("api/v1/unidades_administrativas/get/{clave}")
    public ResponseEntity<UnidadAdministrativa> get(@PathVariable String clave) throws Exception{
        UnidadAdministrativa body = servicio.get(clave);

        return body == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(body);

    }

    @PostMapping("api/v1/unidades_administrativas/create")
    public ResponseEntity<UnidadAdministrativa> create(@RequestBody UnidadAdministrativaRequestDTO request){
        return ResponseEntity.status(201).body(servicio.create(request));
    }

    @PutMapping("api/v1/unidades_administrativas/put/{clave}")
    public ResponseEntity<UnidadAdministrativa> put(@PathVariable String clave, @RequestBody UnidadAdministrativaRequestDTO request) throws Exception {
        UnidadAdministrativa toRequest = servicio.put(clave, request);

        return toRequest == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toRequest);

    }

}
