package com.sistema_expedientes.controllers;

import com.sistema_expedientes.serie_documental.seccion.Seccion;
import com.sistema_expedientes.serie_documental.dto.request.SeccionRequestDTO;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.services.serie_documental.SeccionServicio;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Controlador de Secciones")
@RestController
public class SeccionControlador {

    private final SeccionServicio servicio;

    public SeccionControlador(SeccionServicio servicio) {
        this.servicio = servicio;
    }

    @Operation(summary = "Retorna una seccion por clave")
    @GetMapping("api/v1/secciones/detalles/{clave}")
    public ResponseEntity<Seccion> get(@PathVariable String clave) throws Exception {
        return ResponseEntity.ok(servicio.get(clave));
    }

    @Operation(summary = "Listado de todas las secciones")
    @GetMapping("api/v1/secciones/listar")
    public ResponseEntity<List<Seccion>> listAll(){
        List<Seccion> request = servicio.findByActiveTrue();
        return request.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(request);
    }

    @Operation(summary = "Listado de todas las secciones activas")
    @GetMapping("api/v1/secciones/list_active")
    public ResponseEntity<List<Seccion>> activeSecciones(){
        return ResponseEntity.ok(servicio.findByActiveTrue());
    }

    @Operation(summary = "Listado de todas las secciones eliminadas logicamente")
    @GetMapping("api/secciones/list_deleted")
    public ResponseEntity<List<Seccion>> deletedSecciones(){
        return ResponseEntity.ok(servicio.findByActiveFalse());
    }

    @Operation(summary = "Creacion de una nueva seccion")
    @PostMapping("api/v1/secciones/crear")
    public ResponseEntity<Seccion> create(@RequestBody SeccionRequestDTO request){
        return ResponseEntity.status(201).body(servicio.create(request));
    }

    @Operation(summary = "Restauracion de una seccion eliminada logicamente")
    @PostMapping("api/v1/secciones/restore/{clave}")
    public ResponseEntity<String> restore(@PathVariable String clave) throws ResourceNotFoundException {
        this.servicio.restore(clave);
        return ResponseEntity.ok("Sección restaurada con exito");
    }

    @Operation(summary = "Actualizacion de una seccion por clave")
    @PutMapping("api/v1/secciones/detalles/{clave}/editar")
    public ResponseEntity<Seccion> put(@PathVariable String clave, @RequestBody SeccionRequestDTO request) throws Exception {
        return ResponseEntity.ok(servicio.put(clave, request));
    }

    @Operation(summary = "Eliminacion logica de una seccion por clave")
    @DeleteMapping("api/v1/secciones/delete/{clave}")
    public ResponseEntity<String> softDelete(@PathVariable String clave) throws ResourceNotFoundException {
        this.servicio.softDelete(clave);
        return ResponseEntity.ok("Sección dada de baja con exito");
    }

}
