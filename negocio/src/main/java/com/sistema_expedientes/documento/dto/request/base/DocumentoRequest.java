package com.sistema_expedientes.documento.dto.request.base;

import com.sistema_expedientes.entities.dto.request._DTO;
import org.springframework.web.multipart.MultipartFile;

public class DocumentoRequest extends _DTO {
    private String nombre;
    private MultipartFile file;

    public DocumentoRequest() {
    }

    public DocumentoRequest(String nombre, MultipartFile file) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
