package com.sistema_expedientes.expediente.dto.request.base;

import com.sistema_expedientes.base.request._DTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExpedienteRequest extends _DTO {
    @Max(2100)
    @Min(2000)
    protected Short periodoCierre;
    @NotBlank
    protected String asunto;
    @NotBlank
    protected String tipoExpediente;
    @NotBlank
    protected String numeroProyecto;
    @NotBlank
    protected String nombreProyecto;
    @NotBlank
    protected String acronimoInstitucion;
    @NotBlank
    protected String nombreInstitucion;
    @NotBlank
    protected String numeroContacto;
    @Min(1)
    protected Short cantidadHojas;
    @Min(1)
    @Max(3)
    protected Byte formatoExpediente;
    @Min(1)
    @Max(3)
    protected Byte condicionAcceso;
    @Min(1)
    @Max(3)
    protected Byte tradicionDocumental;
    @Min(1)
    @Max(3)
    protected Byte tipoInformacion;

    public ExpedienteRequest() {
    }

    public ExpedienteRequest(Short periodoCierre, String asunto, String tipoExpediente, String numeroProyecto, String nombreProyecto, String acronimoInstitucion, String nombreInstitucion, String numeroContacto, Short cantidadHojas, Byte formatoExpediente, Byte condicionAcceso, Byte tradicionDocumental, Byte tipoInformacion) {
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

    public String getTipoExpediente() {
        return tipoExpediente;
    }

    public void setTipoExpediente(String tipoExpediente) {
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
