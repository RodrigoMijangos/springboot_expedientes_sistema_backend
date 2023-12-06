package com.sistema_expedientes.legajo.composite_key;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class LegajoCompositeKey implements Serializable {

    @Column(name = "serie_documental_expediente")
    @Min(1)
    @Max(Short.MAX_VALUE)
    private Short identificadorSerieDocumental;
    @NotBlank
    @Column(name = "unidad_administrativa_generadora_expediente")
    private String unidadAdministrativaGeneradora;
    @Min(1)
    @Max(Short.MAX_VALUE)
    @Column(name = "numero_expediente")
    private Short numeroExpediente;
    @NotNull
    @Column(name = "fecha_apertura_expediente")
    private LocalDate fechaApertura;
    @Min(1)
    @Max(Short.MAX_VALUE)
    @Column(name = "numero_legajo")
    private Short numeroLegajo;

    public LegajoCompositeKey() {
    }

    public LegajoCompositeKey(Short identificadorSerieDocumental, String unidadAdministrativaGeneradora, Short numeroExpediente, LocalDate fechaApertura, Short numeroLegajo) {
        this.identificadorSerieDocumental = identificadorSerieDocumental;
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
        this.numeroExpediente = numeroExpediente;
        this.fechaApertura = fechaApertura;
        this.numeroLegajo = numeroLegajo;
    }

    public Short getIdentificadorSerieDocumental() {
        return identificadorSerieDocumental;
    }

    public void setIdentificadorSerieDocumental(Short identificadorSerieDocumental) {
        this.identificadorSerieDocumental = identificadorSerieDocumental;
    }

    public String getUnidadAdministrativaGeneradora() {
        return unidadAdministrativaGeneradora;
    }

    public void setUnidadAdministrativaGeneradora(String unidadAdministrativaGeneradora) {
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
    }

    public Short getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(Short numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Short getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(Short numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }
}