package com.sistema_expedientes.entities.dto.request;

import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;

public class PUTExpedienteRequestDTO extends _DTO {

    private ExpedienteCompositeKey id;
    private Short periodoCierre;
    private String asunto;
    private Short tipoExpediente;
    private String numeroProyecto;
    private String nombreProyecto;
    private String acronimoInstitucion;
    private String nombreInstitucion;
    private String numeroContacto;
    private Short cantidadHojas;
    private Byte formatoExpediente;
    private Byte condicionAcceso;
    private Byte tradicionDocumental;
    private Byte tipoInformacion;

    public PUTExpedienteRequestDTO() {
    }

    public PUTExpedienteRequestDTO(ExpedienteCompositeKey id, Short periodoCierre, String asunto, Short tipoExpediente, String numeroProyecto, String nombreProyecto, String acronimoInstitucion, String nombreInstitucion, String numeroContacto, Short cantidadHojas, Byte formatoExpediente, Byte condicionAcceso, Byte tradicionDocumental, Byte tipoInformacion) {
        this.id = id;
        this.periodoCierre = periodoCierre;
        this.asunto = asunto;
        this.tipoExpediente = tipoExpediente;
        this.numeroProyecto = numeroProyecto;
        this.nombreProyecto = nombreProyecto;
        this.acronimoInstitucion = acronimoInstitucion;
        this.nombreInstitucion = nombreInstitucion;
        this.numeroContacto = numeroContacto;
        this.cantidadHojas = cantidadHojas;
        this.formatoExpediente = formatoExpediente;
        this.condicionAcceso = condicionAcceso;
        this.tradicionDocumental = tradicionDocumental;
        this.tipoInformacion = tipoInformacion;
    }

    public ExpedienteCompositeKey getId() {
        return id;
    }

    public void setId(ExpedienteCompositeKey id) {
        this.id = id;
    }

    public Short getPeriodoCierre() {
        return periodoCierre;
    }

    public void setPeriodoCierre(Short periodoCierre) {
        this.periodoCierre = periodoCierre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Short getTipoExpediente() {
        return tipoExpediente;
    }

    public void setTipoExpediente(Short tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    public String getNumeroProyecto() {
        return numeroProyecto;
    }

    public void setNumeroProyecto(String numeroProyecto) {
        this.numeroProyecto = numeroProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getAcronimoInstitucion() {
        return acronimoInstitucion;
    }

    public void setAcronimoInstitucion(String acronimoInstitucion) {
        this.acronimoInstitucion = acronimoInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public Short getCantidadHojas() {
        return cantidadHojas;
    }

    public void setCantidadHojas(Short cantidadHojas) {
        this.cantidadHojas = cantidadHojas;
    }

    public Byte getFormatoExpediente() {
        return formatoExpediente;
    }

    public void setFormatoExpediente(Byte formatoExpediente) {
        this.formatoExpediente = formatoExpediente;
    }

    public Byte getCondicionAcceso() {
        return condicionAcceso;
    }

    public void setCondicionAcceso(Byte condicionAcceso) {
        this.condicionAcceso = condicionAcceso;
    }

    public Byte getTradicionDocumental() {
        return tradicionDocumental;
    }

    public void setTradicionDocumental(Byte tradicionDocumental) {
        this.tradicionDocumental = tradicionDocumental;
    }

    public Byte getTipoInformacion() {
        return tipoInformacion;
    }

    public void setTipoInformacion(Byte tipoInformacion) {
        this.tipoInformacion = tipoInformacion;
    }
}
