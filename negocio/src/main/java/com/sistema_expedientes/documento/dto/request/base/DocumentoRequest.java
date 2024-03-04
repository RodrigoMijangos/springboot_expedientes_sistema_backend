package com.sistema_expedientes.documento.dto.request.base;

import com.sistema_expedientes.base.request._DTO;
import com.sistema_expedientes.tipos.tipo_documento.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class DocumentoRequest extends _DTO {
    @NotBlank
    private String nombre;
    private Long tipoDocumentoId;

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

    public Long getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(Long tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }
}
