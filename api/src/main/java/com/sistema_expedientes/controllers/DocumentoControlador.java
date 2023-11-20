package com.sistema_expedientes.controllers;

import com.sistema_expedientes.entities.Documento;
import com.sistema_expedientes.entities.dto.request.DocumentoRequest;
import com.sistema_expedientes.services.documento.DocumentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class DocumentoControlador {

    @Autowired
    private DocumentoServicio servicio;

    @GetMapping("api/v1/documentos/list")
    public ResponseEntity<List<Documento>> getAll(){

        List<Documento> lista = this.servicio.getAll();

        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @RequestMapping(path = "api/v1/documentos/crear", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Documento> create(@ModelAttribute DocumentoRequest request) throws IOException {
        return ResponseEntity.status(201).body(this.servicio.create(request));
    }

    @PutMapping("api/v1/documentos/{id}/edit")
    public ResponseEntity<Documento> put(@RequestBody DocumentoRequest request, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.servicio.put(request, id));
    }

    @DeleteMapping("api/v1/documentos/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        this.servicio.delete(id);
        return ResponseEntity.ok("Eliminado");
    }

}
