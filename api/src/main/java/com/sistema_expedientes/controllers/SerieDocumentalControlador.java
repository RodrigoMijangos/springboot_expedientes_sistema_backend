package com.sistema_expedientes.controllers;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import com.sistema_expedientes.serie_documental.dto.request.SerieDocumentalRequestDTO;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.services.serie_documental.SerieDocumentalServicio;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Controlador de Series Documentales")
public class SerieDocumentalControlador {

    private final SerieDocumentalServicio servicio;

    public SerieDocumentalControlador(SerieDocumentalServicio servicio){
        this.servicio = servicio;
    }


    @Operation(summary = "Listado de todos las series documentales")
    @GetMapping("api/v1/series_documentales/listar")
    public ResponseEntity<List<SerieDocumental>> list(){
        List<SerieDocumental> lista = servicio.list();

        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);

    }


    @Operation(summary = "Listado de todas las series documentales activas")
    @GetMapping("api/v1/series_documentales/list_active")
    public ResponseEntity<List<SerieDocumental>> activeUnidades(){
        return ResponseEntity.ok(servicio.findByActiveTrue());
    }

    @Operation(summary = "Listado de todas las series documentales eliminadas logicamente")
    @GetMapping("api/series_documentales/list_deleted")
    public ResponseEntity<List<SerieDocumental>> deletedUnidades(){
        return ResponseEntity.ok(servicio.findByActiveFalse());
    }
    @Operation(summary = "Retorna una serie documental por id")
    @GetMapping("api/v1/series_documentales/get/{id}")
    public ResponseEntity<SerieDocumental> get(@PathVariable Short id) throws ResourceNotFoundException {
        SerieDocumental response = servicio.get(id);

        return ResponseEntity.ok(response);

    }

    @Operation(summary = "Creacion de una nueva serie documental")
    @PostMapping("api/v1/series_documentales/create")
    public ResponseEntity<SerieDocumental> create(@Valid @RequestBody SerieDocumentalRequestDTO request) throws Exception{

        return ResponseEntity.status(201).body(servicio.create(request));

    }

    @Operation(summary = "Restaura una serie documental por id")
    @DeleteMapping("api/v1/series_documentales/restore/{id}")
    public ResponseEntity<String> restore(@PathVariable Short id) throws ResourceNotFoundException {
        this.servicio.softDelete(id);
        return ResponseEntity.ok("Serie documental dada de baja con exito");
    }

    @Operation(summary = "Actualziacion de una serie documental por id")
    @PutMapping("api/v1/series_documentales/put/{id}")
    public ResponseEntity<SerieDocumental> put(@PathVariable Short id, @Valid @RequestBody SerieDocumentalRequestDTO request){
        SerieDocumental response = servicio.put(id, request);

        return response == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);

    }

    @Operation(summary = "Eliminado logico de una serie documental por id")
    @DeleteMapping("api/v1/series_documentales/delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Short id) throws ResourceNotFoundException {
        this.servicio.softDelete(id);
        return ResponseEntity.ok("Serie documental dada de baja con exito");
    }

}
