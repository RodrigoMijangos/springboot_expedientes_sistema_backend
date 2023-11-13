package com.sistema_expedientes.controllers;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.CreateExpedienteRequestDTO;
import com.sistema_expedientes.entities.dto.request.ListaLegajosExpedienteRequestDTO;
import com.sistema_expedientes.entities.dto.request.PUTExpedienteRequestDTO;
import com.sistema_expedientes.services.expediente.ExpedienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpedienteControlador {

    @Autowired
    private ExpedienteServicio servicio;

    @GetMapping("api/v1/expedientes/list")
    public ResponseEntity<List<Expediente>> list(){
        List<Expediente> response = servicio.list();

        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);

    }

    @PostMapping("api/v1/expedientes/get")
    public ResponseEntity<Expediente> get(@RequestBody ExpedienteCompositeKey id) throws Exception {
        return ResponseEntity.ok(servicio.get(id));
    }

    @PostMapping("api/v1/expedientes/create")
    public ResponseEntity<Expediente> create(@RequestBody CreateExpedienteRequestDTO request){

        Expediente to_bd = servicio.create(request);

        return to_bd == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(201).body(to_bd);

    }

    @PostMapping("api/v1/expedientes/crear_lista_legajos")
    public ResponseEntity<Expediente> createList(@RequestBody ListaLegajosExpedienteRequestDTO request) throws Exception {
        return ResponseEntity.ok(this.servicio.guardarListaLegajos(request));
    }

    @PutMapping("api/v1/expedientes/edit")
    public ResponseEntity<Expediente> put(@RequestBody PUTExpedienteRequestDTO request) throws Exception {
        Expediente to_bd = servicio.put(request);

        return to_bd == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(to_bd);

    }

    @DeleteMapping("api/v1/expedientes/delete")
    public ResponseEntity<String> delete(@RequestBody ExpedienteCompositeKey id) throws Exception {
        this.servicio.delete(id);
        return ResponseEntity.ok("Ha sido eliminado");
    }

}
