package com.sistema_expedientes.controllers;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import com.sistema_expedientes.serie_documental.dto.request.SerieDocumentalRequestDTO;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.services.serie_documental.SerieDocumentalServicio;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SerieDocumentalControlador {

    private final SerieDocumentalServicio servicio;

    public SerieDocumentalControlador(SerieDocumentalServicio servicio){
        this.servicio = servicio;
    }

    @GetMapping("api/v1/series_documentales/listar")
    public ResponseEntity<List<SerieDocumental>> list(){
        List<SerieDocumental> lista = servicio.list();

        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);

    }


    @GetMapping("api/v1/series_documentales/list_active")
    public ResponseEntity<List<SerieDocumental>> activeUnidades(){
        return ResponseEntity.ok(servicio.findByActiveTrue());
    }

    @GetMapping("api/series_documentales/list_deleted")
    public ResponseEntity<List<SerieDocumental>> deletedUnidades(){
        return ResponseEntity.ok(servicio.findByActiveFalse());
    }
    @GetMapping("api/v1/series_documentales/get/{id}")
    public ResponseEntity<SerieDocumental> get(@PathVariable Short id) throws ResourceNotFoundException {
        SerieDocumental response = servicio.get(id);

        return ResponseEntity.ok(response);

    }

    @PostMapping("api/v1/series_documentales/create")
    public ResponseEntity<SerieDocumental> create(@Valid @RequestBody SerieDocumentalRequestDTO request) throws Exception{

        return ResponseEntity.status(201).body(servicio.create(request));

    }

    @PutMapping("api/v1/series_documentales/put/{id}")
    public ResponseEntity<SerieDocumental> put(@PathVariable Short id, @Valid @RequestBody SerieDocumentalRequestDTO request){
        SerieDocumental response = servicio.put(id, request);

        return response == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);

    }

    @DeleteMapping("api/v1/series_documentales/delete/{clave}")
    public ResponseEntity<String> softDelete(@PathVariable Short clave) throws ResourceNotFoundException {
        this.servicio.softDelete(clave);
        return ResponseEntity.ok("Serie documental dada de baja con exito");
    }



}
