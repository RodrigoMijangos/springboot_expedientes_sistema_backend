package com.sistema_expedientes.entities.compositesKeys;

import java.io.Serializable;
import java.util.Objects;

public class ExpedienteCompositeKey implements Serializable {

    private Short identificadorNumerico;

    private Short periodoApertura;

    private Byte unidadAdministrativa;

    private Short serieDocumental;

    public ExpedienteCompositeKey() {
    }

    public ExpedienteCompositeKey(Short identificadorNumerico, Short periodoApertura, Byte unidadAdministrativa, Short serieDocumental) {
        this.identificadorNumerico = identificadorNumerico;
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

    public Short getSerieDocumental() {
        return serieDocumental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpedienteCompositeKey that)) return false;
        return Objects.equals(getIdentificadorNumerico(), that.getIdentificadorNumerico()) && Objects.equals(getPeriodoApertura(), that.getPeriodoApertura()) && Objects.equals(getUnidadAdministrativa(), that.getUnidadAdministrativa()) && Objects.equals(getSerieDocumental(), that.getSerieDocumental());
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificadorNumerico, periodoApertura, unidadAdministrativa, serieDocumental);
    }
}
