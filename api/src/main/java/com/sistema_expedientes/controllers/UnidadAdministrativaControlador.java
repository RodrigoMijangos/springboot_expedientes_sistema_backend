package com.sistema_expedientes.controllers;

import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import com.sistema_expedientes.unidad_administrativa.dto.request.UnidadAdministrativaRequestDTO;
import com.sistema_expedientes.services.unidad_administrativa.UnidadAdministrativaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Controlador de Unidades Administrativas")
@RestController
public class UnidadAdministrativaControlador {

    private final UnidadAdministrativaServicio servicio;

    public UnidadAdministrativaControlador(UnidadAdministrativaServicio servicio){
        this.servicio = servicio;
    }

    @Operation(summary = "Listado de todas las unidades administrativas")
    @GetMapping("api/v1/unidades_administrativas/list")
    public ResponseEntity<List<UnidadAdministrativa>> index(){
        return ResponseEntity.ok(servicio.list());
    }

    @Operation(summary = "Listado de todas las unidades administrativas activas")
    @GetMapping("api/v1/unidades_administrativas/list_active")
    public ResponseEntity<List<UnidadAdministrativa>> activeUnidades(){
        return ResponseEntity.ok(servicio.findByActiveTrue());
    }

    @Operation(summary = "Listado de todas las unidades eliminadas logicamente")
    @GetMapping("api/unidades_administrativas/list_deleted")
    public ResponseEntity<List<UnidadAdministrativa>> deletedUnidades(){
        return ResponseEntity.ok(servicio.findByActiveFalse());
    }

    @Operation(summary = "Retorna una unidad administrativa por id")
    @GetMapping("api/v1/unidades_administrativas/get/{clave}")
    public ResponseEntity<UnidadAdministrativa> get(@PathVariable @Size(max = 5) String clave) throws ResourceNotFoundException {
        return ResponseEntity.ok(servicio.get(clave));

    }

    @Operation(summary = "Creacion de una nueva unidad administrativa")
    @PostMapping("api/v1/unidades_administrativas/create")
    public ResponseEntity<UnidadAdministrativa> create(@Valid @RequestBody UnidadAdministrativaRequestDTO request) throws ResourceNotFoundException, IOException {
        return ResponseEntity.status(201).body(servicio.create(request));
    }

    @Operation(summary = "Restauracion de una unidad administrativa eliminada logicamente")
    @PostMapping("api/v1/unidades_administrativas/restore/{clave}")
    public ResponseEntity<String> restore(@PathVariable @Size(max = 5) String clave) throws ResourceNotFoundException {
        this.servicio.restore(clave);
        return ResponseEntity.ok("Unidad restaurada con exito");
    }

    @Operation(summary = "Actualizacion de una unidad adminitrativa por id")
    @PutMapping("api/v1/unidades_administrativas/put/{clave}")
    public ResponseEntity<UnidadAdministrativa> put(@PathVariable @Size(max = 5) String clave, @Valid @RequestBody UnidadAdministrativaRequestDTO request) throws ResourceNotFoundException {
        return ResponseEntity.ok(servicio.put(clave, request));

    }

    @Operation(summary = "Eliminado permanente de una unidad administrativa por id")
    @DeleteMapping("api/v1/unidades_administrativas/delete/{clave}")
    public ResponseEntity<String> delete(@PathVariable @Size(max = 5) String clave) throws ResourceNotFoundException {
        this.servicio.delete(clave);
        return ResponseEntity.ok("El recurso ha sido eliminado");
    }

    @Operation(summary = "Eliminacion logica de una unidad administrativa por id")
    @DeleteMapping("api/v1/unidades_administrativas/softDelete/{clave}")
    public ResponseEntity<String> softDelete(@PathVariable @Size(max = 5) String clave) throws ResourceNotFoundException {
        this.servicio.softDelete(clave);
        return ResponseEntity.ok("Unidad dada de baja con exito");
    }

}
