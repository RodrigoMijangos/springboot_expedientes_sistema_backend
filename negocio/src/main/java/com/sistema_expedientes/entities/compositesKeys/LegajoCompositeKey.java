package com.sistema_expedientes.entities.compositesKeys;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class LegajoCompositeKey implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(name = "identificadorNumerico", column = @Column(name = "identificador_expediente")),
            @AttributeOverride(name = "periodoApertura", column = @Column(name = "periodo_apertura")),
            @AttributeOverride(name = "unidadAdministrativa", column = @Column(name = "unidad_administrativa_expediente")),
            @AttributeOverride(name = "serieDocumental", column = @Column(name = "serie_documental_expediente"))
    })
    private ExpedienteCompositeKey expedienteId;

    @Column(name = "identificador_documento")
    private Long documentoId;

    public LegajoCompositeKey() {
    }

    public LegajoCompositeKey(ExpedienteCompositeKey expedienteId, Long documentoId) {
        this.expedienteId = expedienteId;
        this.documentoId = documentoId;
    }

    public ExpedienteCompositeKey getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(ExpedienteCompositeKey expedienteId) {
        this.expedienteId = expedienteId;
    }

    public Long getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Long documentoId) {
        this.documentoId = documentoId;
    }
}
