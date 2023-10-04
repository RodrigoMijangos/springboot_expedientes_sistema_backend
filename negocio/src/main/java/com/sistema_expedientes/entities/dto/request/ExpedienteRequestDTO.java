package com.sistema_expedientes.entities.dto.request;

public class ExpedienteRequestDTO extends _DTO {

    private Short identificador_numerico;
    private Short periodo_apertura;
    private Byte unidad_administrativa;
    private Short serie_documental;
    private Short periodo_cierre;

    public ExpedienteRequestDTO() {
    }

    public ExpedienteRequestDTO(Short identificador_numerico, Short periodo_apertura, Byte unidad_administrativa, Short serie_documental, Short periodo_cierre) {
        this.identificador_numerico = identificador_numerico;
        this.periodo_apertura = periodo_apertura;
        this.unidad_administrativa = unidad_administrativa;
        this.serie_documental = serie_documental;
        this.periodo_cierre = periodo_cierre;
    }

    public ExpedienteRequestDTO(Short periodo_apertura, Byte unidad_administrativa, Short serie_documental, Short periodo_cierre) {
        this.periodo_apertura = periodo_apertura;
        this.unidad_administrativa = unidad_administrativa;
        this.serie_documental = serie_documental;
        this.periodo_cierre = periodo_cierre;
    }

    public ExpedienteRequestDTO(Short periodo_apertura, Byte unidad_administrativa, Short serie_documental) {
        this.periodo_apertura = periodo_apertura;
        this.unidad_administrativa = unidad_administrativa;
        this.serie_documental = serie_documental;
    }

    public Short getIdentificador_numerico() {
        return identificador_numerico;
    }

    public void setIdentificador_numerico(Short identificador_numerico) {
        this.identificador_numerico = identificador_numerico;
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

    public void setPeriodo_cierre(Short periodo_cierre) {
        this.periodo_cierre = periodo_cierre;
    }
}
