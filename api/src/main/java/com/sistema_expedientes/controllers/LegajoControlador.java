package com.sistema_expedientes.controllers;

import com.sistema_expedientes.entities.Documento;
import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import com.sistema_expedientes.entities.dto.request.CreateLegajoRequestDTO;
import com.sistema_expedientes.entities.dto.request.ListaDocumentosLegajoRequestDTO;
import com.sistema_expedientes.entities.dto.request.PUTLegajoRequestDTO;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LegajoControlador {

    @Autowired
    private LegajoServicio servicio;

    @PostMapping("api/v1/legajos/crear")
    public ResponseEntity<Legajo> create(@RequestBody CreateLegajoRequestDTO request) throws Exception {
        return ResponseEntity.status(201).body(this.servicio.create(request));
    }

    @PutMapping("api/v1/legajos/editar")
    public ResponseEntity<Legajo> put(@RequestBody PUTLegajoRequestDTO request) throws Exception{
        return ResponseEntity.ok(this.servicio.put(request));
    }

    @PostMapping("api/v1/legajos/insertar_documento")
    public ResponseEntity<Documento> crearEInsertarDocumento(){
        return null;
    }

    @PostMapping("api/v1/legajos/guardar_lista_documentos")
    public ResponseEntity<Legajo> crearEInsertarListaDocumentos(@RequestBody ListaDocumentosLegajoRequestDTO request) throws Exception {

        return ResponseEntity.ok(this.servicio.guardarListaDocumentos(request));

    }

    @DeleteMapping("api/v1/legajos/borrar")
    public ResponseEntity<String> delete(@RequestBody LegajoCompositeKey id) throws Exception{

        this.servicio.delete(id);

        return ResponseEntity.ok("El registro fue eliminado");
    }

}
