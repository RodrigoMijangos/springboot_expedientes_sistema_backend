package com.sistema_expedientes.controllers;

import com.sistema_expedientes.documento.Documento;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.legajo.composite_key.LegajoCompositeKey;
import com.sistema_expedientes.legajo.dto.request.specific.CreateDocumentInsideLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.ListaDocumentosLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.PUTLegajoRequestDTO;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class LegajoControlador {

    private final LegajoServicio servicio;

    public LegajoControlador(LegajoServicio servicio) {
        this.servicio = servicio;
    }

    @PutMapping("api/v1/legajos/editar")
    public ResponseEntity<Legajo> put(@RequestBody PUTLegajoRequestDTO request) throws Exception{
        return ResponseEntity.ok(this.servicio.put(request));
    }

    @PostMapping(value = "api/v1/legajos/insertar_documento", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Legajo> crearEInsertarDocumento(@RequestPart CreateDocumentInsideLegajoRequestDTO request, @RequestPart MultipartFile file) throws Exception {
        return ResponseEntity.status(201).body(this.servicio.guardarDocumento(request, file));
    }

    @PostMapping(value = "api/v1/legajos/guardar_lista_documentos", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Legajo> crearEInsertarListaDocumentos(@RequestPart ListaDocumentosLegajoRequestDTO request, @RequestPart MultipartFile[] files) throws Exception {
        return ResponseEntity.ok(this.servicio.guardarListaDocumentos(request, files));
    }

    @DeleteMapping("api/v1/legajos/borrar")
    public ResponseEntity<String> delete(@RequestBody LegajoCompositeKey id) throws Exception{

        this.servicio.delete(id);

        return ResponseEntity.ok("El registro fzue eliminado");
    }

}
