package com.sistema_expedientes.controllers;

import com.sistema_expedientes.documento.Documento;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.legajo.composite_key.LegajoCompositeKey;
import com.sistema_expedientes.legajo.dto.request.specific.CreateDocumentInsideLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.ListaDocumentosLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.PUTLegajoRequestDTO;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Controlador de Legajos")
@RestController
public class LegajoControlador {

    private final LegajoServicio servicio;

    public LegajoControlador(LegajoServicio servicio) {
        this.servicio = servicio;
    }

    @Operation(summary = "Actualizacion de un legajo por id compuesto")
    @PutMapping("api/v1/legajos/editar")
    public ResponseEntity<Legajo> put(@Valid @RequestBody PUTLegajoRequestDTO request) throws Exception{
        return ResponseEntity.ok(this.servicio.put(request));
    }

    @Operation(summary = "Carga de un documento en legajo por id compuesto")
    @PostMapping(value = "api/v1/legajos/insertar_documento", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Legajo> crearEInsertarDocumento(@RequestPart @Valid CreateDocumentInsideLegajoRequestDTO request, @RequestPart MultipartFile file) throws IOException, ResourceNotFoundException {
        return ResponseEntity.status(201).body(this.servicio.guardarDocumento(request, file));
    }

    @Operation(summary = "Carga multiple de documentos en legajo por id compuesto")
    @PostMapping(value = "api/v1/legajos/guardar_lista_documentos", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Legajo> crearEInsertarListaDocumentos(@Valid @RequestPart ListaDocumentosLegajoRequestDTO request, @RequestPart MultipartFile[] files) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.servicio.guardarListaDocumentos(request, files));
    }

    @Operation(summary = "Eliminacion de un legajo por id compuesto")
    @DeleteMapping("api/v1/legajos/borrar")
    public ResponseEntity<String> delete(@RequestBody LegajoCompositeKey id) throws IOException, ResourceNotFoundException {

        this.servicio.delete(id);

        return ResponseEntity.ok("El registro fue eliminado");
    }

}
