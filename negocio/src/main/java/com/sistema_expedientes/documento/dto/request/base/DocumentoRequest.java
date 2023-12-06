package com.sistema_expedientes.documento.dto.request.base;

import com.sistema_expedientes.base.request._DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class DocumentoRequest extends _DTO {
    @NotBlank
    private String nombre;

    public DocumentoRequest() {
    }

    public DocumentoRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
