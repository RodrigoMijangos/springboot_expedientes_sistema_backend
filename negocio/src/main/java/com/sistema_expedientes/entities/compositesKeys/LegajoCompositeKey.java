package com.sistema_expedientes.entities.compositesKeys;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class LegajoCompositeKey implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(name = "identificadorNumerico", column = @Column(name = "numero_expediente")),
            @AttributeOverride(name = "periodoApertura", column = @Column(name = "periodo_apertura_expediente")),
            @AttributeOverride(name = "unidadAdministrativa", column = @Column(name = "unidad_administrativa_generadora_expediente")),
            @AttributeOverride(name = "serieDocumental", column = @Column(name = "serie_documental_expediente"))
    })
    private ExpedienteCompositeKey expedienteId;

    @Column(name = "numero_legajo")
    private Short numeroLegajo;

    public LegajoCompositeKey() {
    }

    public LegajoCompositeKey(ExpedienteCompositeKey expedienteId, Short numeroLegajo) {
        this.expedienteId = expedienteId;
        this.numeroLegajo = numeroLegajo;
    }

    public ExpedienteCompositeKey getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(ExpedienteCompositeKey expedienteId) {
        this.expedienteId = expedienteId;
    }

    public Short getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(Short numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }
}