package com.sistema_expedientes.entities;

import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "expedientes")
@IdClass(ExpedienteCompositeKey.class)
public class Expediente {

    @Id
    @Column(name = "identificador_numerico")
    @NotNull
    private Short identificadorNumerico;

    @Id
    @Column(name = "periodo_apertura")
    @NotNull
    private Short periodoApertura;

    @Id
    @Column(name = "unidad_administrativa")
    @NotNull
    private Byte unidadAdministrativa;

    @Id
    @Column(name = "serie_documental")
    @NotNull
    private Short serieDocumental;

    @Column(name = "periodo_cierre", nullable = false)
    private Short periodoCierre;

    public Expediente() {
    }

    public Expediente(Short identificadorNumerico, Short periodoApertura, Byte unidadAdministrativa, Short serieDocumental, Short periodoCierre) {
        this.identificadorNumerico = identificadorNumerico;
        this.periodoApertura = periodoApertura;
        this.unidadAdministrativa = unidadAdministrativa;
        this.serieDocumental = serieDocumental;
        this.periodoCierre = periodoCierre;
    }

    public Expediente(Short periodoApertura, Byte unidadAdministrativa, Short serieDocumental, Short periodoCierre) {
        this.periodoApertura = periodoApertura;
        this.unidadAdministrativa = unidadAdministrativa;
        this.serieDocumental = serieDocumental;
        this.periodoCierre = periodoCierre;
    }

    public Expediente(Short periodoApertura, Byte unidadAdministrativa, Short serieDocumental) {
        this.periodoApertura = periodoApertura;
        this.unidadAdministrativa = unidadAdministrativa;
        this.serieDocumental = serieDocumental;
    }

    public Short getIdentificadorNumerico() {
        return identificadorNumerico;
    }

    public Short getPeriodoApertura() {
        return periodoApertura;
    }

    public Byte getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(Byte unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public Short getSerieDocumental() {
        return serieDocumental;
    }

    public void setSerieDocumental(Short serieDocumental) {
        this.serieDocumental = serieDocumental;
    }

    public Short getPeriodoCierre() {
        return periodoCierre;
    }

    public void setPeriodoCierre(Short periodoCierre) {
        this.periodoCierre = periodoCierre;
    }
}
