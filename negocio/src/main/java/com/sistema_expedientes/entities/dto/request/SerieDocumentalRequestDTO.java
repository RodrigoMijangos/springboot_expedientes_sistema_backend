package com.sistema_expedientes.entities.dto.request;

public class SerieDocumentalRequestDTO {

    private Short serie_padre;
    private String seccion;
    private String clave;
    private String nombre;
    private Boolean valorDocumentalAdministrativo;
    private Boolean valorDocumentalLegal;
    private Boolean valorDocumentalContable;
    private Short periodosEnConcentracion;
    private Short periodosEnTramite;
    private Byte tecnicaSeleccion;
    private String observaciones;

    public SerieDocumentalRequestDTO() {
    }

    public SerieDocumentalRequestDTO(Short serie_padre, String seccion, String clave, String nombre, Boolean valorDocumentalAdministrativo, Boolean valorDocumentalLegal, Boolean valorDocumentalContable, Short periodosEnConcentracion, Short periodosEnTramite, Byte tecnicaSeleccion, String observaciones) {
        this.serie_padre = serie_padre;
        this.seccion = seccion;
        this.clave = clave;
        this.nombre = nombre;
        this.valorDocumentalAdministrativo = valorDocumentalAdministrativo;
        this.valorDocumentalLegal = valorDocumentalLegal;
        this.valorDocumentalContable = valorDocumentalContable;
        this.periodosEnTramite = periodosEnTramite;
        this.periodosEnConcentracion = periodosEnConcentracion;
        this.tecnicaSeleccion = tecnicaSeleccion;
        this.observaciones = observaciones;
    }

    public Short getSerie_padre() {
        return serie_padre;
    }

    public void setSerie_padre(Short serie_padre) {
        this.serie_padre = serie_padre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getValorDocumentalAdministrativo() {
        return valorDocumentalAdministrativo;
    }

    public void setValorDocumentalAdministrativo(Boolean valorDocumentalAdministrativo) {
        this.valorDocumentalAdministrativo = valorDocumentalAdministrativo;
    }

    public Boolean getValorDocumentalLegal() {
        return valorDocumentalLegal;
    }

    public void setValorDocumentalLegal(Boolean valorDocumentalLegal) {
        this.valorDocumentalLegal = valorDocumentalLegal;
    }

    public Boolean getValorDocumentalContable() {
        return valorDocumentalContable;
    }

    public void setValorDocumentalContable(Boolean valorDocumentalContable) {
        this.valorDocumentalContable = valorDocumentalContable;
    }

    public Short getPeriodosEnTramite() {
        return periodosEnTramite;
    }

    public void setPeriodosEnTramite(Short periodosEnTramite) {
        this.periodosEnTramite = periodosEnTramite;
    }

    public Short getPeriodosEnConcentracion() {
        return periodosEnConcentracion;
    }

    public void setPeriodosEnConcentracion(Short periodosEnConcentracion) {
        this.periodosEnConcentracion = periodosEnConcentracion;
    }

    public Byte getTecnicaSeleccion() {
        return tecnicaSeleccion;
    }

    public void setTecnicaSeleccion(Byte tecnicaSeleccion) {
        this.tecnicaSeleccion = tecnicaSeleccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}