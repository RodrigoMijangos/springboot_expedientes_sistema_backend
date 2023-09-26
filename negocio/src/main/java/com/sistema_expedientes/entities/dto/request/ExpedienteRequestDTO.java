package com.sistema_expedientes.entities.dto.request;

public class ExpedienteRequestDTO extends _DTO{

    private final Short periodo_apertura;
    private final Byte unidad_administrativa;
    private final Short serie_documental;
    private final Short periodo_cierre;

    public ExpedienteRequestDTO(Short periodo_apertura, Byte unidad_administrativa, Short serie_documental, Short periodo_cierre) {
        this.periodo_apertura = periodo_apertura;
        this.unidad_administrativa = unidad_administrativa;
        this.serie_documental = serie_documental;
        this.periodo_cierre = periodo_cierre;
    }

    public Short getPeriodo_apertura() {
        return periodo_apertura;
    }

    public Byte getUnidad_administrativa() {
        return unidad_administrativa;
    }

    public Short getSerie_documental() {
        return serie_documental;
    }

    public Short getPeriodo_cierre() {
        return periodo_cierre;
    }

}
