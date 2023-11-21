package com.sistema_expedientes.legajo.dto.request.specific;

import com.sistema_expedientes.documento.dto.request.base.DocumentoRequest;
import com.sistema_expedientes.legajo.composite_key.LegajoCompositeKey;

import java.util.List;

public class ListaDocumentosLegajoRequestDTO {

    private LegajoCompositeKey legajo;
    private List<DocumentoRequest> documentos;

    public ListaDocumentosLegajoRequestDTO() {
    }

    public ListaDocumentosLegajoRequestDTO(LegajoCompositeKey legajo, List<DocumentoRequest> documentos) {
        this.legajo = legajo;
        this.documentos = documentos;
    }

    public LegajoCompositeKey getLegajo() {
        return legajo;
    }

    public void setLegajo(LegajoCompositeKey legajo) {
        this.legajo = legajo;
    }

    public List<DocumentoRequest> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoRequest> documentos) {
        this.documentos = documentos;
    }
}
