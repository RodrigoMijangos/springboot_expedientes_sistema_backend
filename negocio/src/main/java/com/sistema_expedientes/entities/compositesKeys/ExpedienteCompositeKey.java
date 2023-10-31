package com.sistema_expedientes.entities.compositesKeys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ExpedienteCompositeKey implements Serializable {

    private Short numeroExpediente;

    private LocalDate fechaApertura;

    private String unidadAdministrativaGeneradora;

    private Short identificadorSerieDocumental;

    public ExpedienteCompositeKey() {
    }

    public ExpedienteCompositeKey(Short numeroExpediente, LocalDate fechaApertura, String unidadAdministrativaGeneradora, Short identificadorSerieDocumental) {
        this.numeroExpediente = numeroExpediente;
        this.fechaApertura = fechaApertura;
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
        this.identificadorSerieDocumental = identificadorSerieDocumental;
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

    public String getUnidadAdministrativaGeneradora() {
        return unidadAdministrativaGeneradora;
    }

    public void setUnidadAdministrativaGeneradora(String unidadAdministrativaGeneradora) {
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
    }

    public Short getIdentificadorSerieDocumental() {
        return identificadorSerieDocumental;
    }

    public void setIdentificadorSerieDocumental(Short identificadorSerieDocumental) {
        this.identificadorSerieDocumental = identificadorSerieDocumental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpedienteCompositeKey that)) return false;
        return Objects.equals(getNumeroExpediente(), that.getNumeroExpediente()) && Objects.equals(getFechaApertura(), that.getFechaApertura()) && Objects.equals(getUnidadAdministrativaGeneradora(), that.getUnidadAdministrativaGeneradora()) && Objects.equals(getIdentificadorSerieDocumental(), that.getIdentificadorSerieDocumental());
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroExpediente, fechaApertura, unidadAdministrativaGeneradora, identificadorSerieDocumental);
    }
}
