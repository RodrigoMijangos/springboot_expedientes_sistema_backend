package com.sistema_expedientes.controllers;

import com.sistema_expedientes.documento.Documento;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.legajo.composite_key.LegajoCompositeKey;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.ListaDocumentosLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.PUTLegajoRequestDTO;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LegajoControlador {

    @Autowired
    private LegajoServicio servicio;

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
