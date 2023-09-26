package com.sistema_expedientes.entities.dto.request;

public class SerieDocumentalRequestDTO {

    private final Short id;
    private final String nombre;
    private final String serie;
    private Short serie_padre = null;
    private Boolean valor_documental_legal = false;
    private Boolean valor_documental_administrativo = false;
    private Boolean valor_documental_contable = false;
    private final Byte en_tramite;
    private final Byte en_concentracion;
    private final Byte procedimiento_final;
    private String observaciones = "";

    public SerieDocumentalRequestDTO(Short id, String nombre, String serie, Byte en_tramite, Byte en_concentracion, Byte procedimiento_final) {
        this.id = id;
        this.nombre = nombre;
        this.serie = serie;
        this.en_tramite = en_tramite;
        this.en_concentracion = en_concentracion;
        this.procedimiento_final = procedimiento_final;
    }

    public SerieDocumentalRequestDTO(Short id, String nombre, String serie, Short serie_padre, Boolean valor_documental_legal, Boolean valor_documental_administrativo, Boolean valor_documental_contable, Byte en_tramite, Byte en_concentracion, Byte procedimiento_final, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.serie = serie;
        this.serie_padre = serie_padre;
        this.valor_documental_legal = valor_documental_legal;
        this.valor_documental_administrativo = valor_documental_administrativo;
        this.valor_documental_contable = valor_documental_contable;
        this.en_tramite = en_tramite;
        this.en_concentracion = en_concentracion;
        this.procedimiento_final = procedimiento_final;
        this.observaciones = observaciones;
    }

    public Short getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSerie() {
        return serie;
    }

    public Short getSerie_padre() {
        return serie_padre;
    }

    public Boolean getValor_documental_legal() {
        return valor_documental_legal;
    }

    public Boolean getValor_documental_administrativo() {
        return valor_documental_administrativo;
    }

    public Boolean getValor_documental_contable() {
        return valor_documental_contable;
    }

    public Byte getEn_tramite() {
        return en_tramite;
    }

    public Byte getEn_concentracion() {
        return en_concentracion;
    }

    public Byte getProcedimiento_final() {
        return procedimiento_final;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
