package com.sistema_expedientes.legajo.dto.request.specific;

import com.sistema_expedientes.documento.dto.request.base.DocumentoRequest;
import com.sistema_expedientes.base.request._DTO;
import com.sistema_expedientes.legajo.composite_key.LegajoCompositeKey;

public class CreateDocumentInsideLegajoRequestDTO extends _DTO {

    private LegajoCompositeKey legajo;
    private DocumentoRequest documento;

    public CreateDocumentInsideLegajoRequestDTO(LegajoCompositeKey legajo, DocumentoRequest documento) {
        this.legajo = legajo;
        this.documento = documento;
    }

    public LegajoCompositeKey getLegajo() {
        return legajo;
    }

    public void setLegajo(LegajoCompositeKey legajo) {
        this.legajo = legajo;
    }

    public DocumentoRequest getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoRequest documento) {
        this.documento = documento;
    }
}
