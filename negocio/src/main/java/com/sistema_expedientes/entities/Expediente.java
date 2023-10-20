package com.sistema_expedientes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.converters.CondicionAccesoExpedienteConverter;
import com.sistema_expedientes.entities.converters.FormatoExpedienteConverter;
import com.sistema_expedientes.entities.converters.TipoInformacionExpedienteConverter;
import com.sistema_expedientes.entities.converters.TradicionDocumentalExpedienteConverter;
import com.sistema_expedientes.entities.enumerates.CondicionAccesoExpediente;
import com.sistema_expedientes.entities.enumerates.FormatoExpediente;
import com.sistema_expedientes.entities.enumerates.TipoInformacionExpediente;
import com.sistema_expedientes.entities.enumerates.TradicionDocumentalExpediente;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expedientes")
@IdClass(ExpedienteCompositeKey.class)
public class Expediente {
    @Id
    @Column(name = "serie_documental")
    private Short identificadorSerieDocumental;
    @Id
    @Column(name = "unidad_administrativa_generadora")
    private String unidadAdministrativaGeneradora;
    @Id
    @Column(name = "numero_expediente")
    private Short numeroExpediente;
    @Id
    @Column(name = "fecha_apertura")
    private LocalDate fechaApertura;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_documental")
    @JsonIgnore
    private SerieDocumental serieDocumental;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_administrativa_generadora")
    @JsonIgnore
    private UnidadAdministrativa unidadAdministrativa;
    @Column(name = "periodo_cierre")
    private Short periodoCierre;
    private String asunto;
    @Column(name = "tipo_expediente")
    private String tipoExpediente;
    @Column(name = "numero_proyecto")
    private String numeroProyecto;
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    @Column(name = "acronimo_institucion")
    private String acronimoInstitucion;
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    @Column(name = "numero_contrato")
    private String numeroContacto;
    @Column(name = "cantidad_hojas")
    private Short cantidadHojas;
    @Convert(converter = FormatoExpedienteConverter.class)
    @Column(name = "tipo_formato")
    private FormatoExpediente formatoExpediente;
    @Convert(converter = CondicionAccesoExpedienteConverter.class)
    @Column(name = "condicion_acceso")
    private CondicionAccesoExpediente condicionAcceso;
    @Convert(converter = TradicionDocumentalExpedienteConverter.class)
    @Column(name = "tradicion_documental")
    private TradicionDocumentalExpediente tradicionDocumental;
    @Convert(converter = TipoInformacionExpedienteConverter.class)
    @Column(name = "tipo_informacion")
    private TipoInformacionExpediente tipoInformacion;


    public Expediente() {
    }

    public Expediente(Short identificadorSerieDocumental, String unidadAdministrativaGeneradora, Short numeroExpediente, LocalDate fechaApertura, SerieDocumental serieDocumental, UnidadAdministrativa unidadAdministrativa, Short periodoCierre, String asunto, String tipoExpediente, String numeroProyecto, String nombreProyecto, String acronimoInstitucion, String nombreInstitucion, String numeroContacto, Short cantidadHojas, FormatoExpediente formatoExpediente, CondicionAccesoExpediente condicionAcceso, TradicionDocumentalExpediente tradicionDocumental, TipoInformacionExpediente tipoInformacion) {
        this.identificadorSerieDocumental = identificadorSerieDocumental;
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
        this.numeroExpediente = numeroExpediente;
        this.fechaApertura = fechaApertura;
        this.serieDocumental = serieDocumental;
        this.unidadAdministrativa = unidadAdministrativa;
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

    public SerieDocumental getSerieDocumental() {
        return serieDocumental;
    }

    public void setSerieDocumental(SerieDocumental serieDocumental) {
        this.serieDocumental = serieDocumental;
    }

    public UnidadAdministrativa getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
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

    public FormatoExpediente getFormatoExpediente() {
        return formatoExpediente;
    }

    public void setFormatoExpediente(FormatoExpediente formatoExpediente) {
        this.formatoExpediente = formatoExpediente;
    }

    public CondicionAccesoExpediente getCondicionAcceso() {
        return condicionAcceso;
    }

    public void setCondicionAcceso(CondicionAccesoExpediente condicionAcceso) {
        this.condicionAcceso = condicionAcceso;
    }

    public TradicionDocumentalExpediente getTradicionDocumental() {
        return tradicionDocumental;
    }

    public void setTradicionDocumental(TradicionDocumentalExpediente tradicionDocumental) {
        this.tradicionDocumental = tradicionDocumental;
    }

    public TipoInformacionExpediente getTipoInformacion() {
        return tipoInformacion;
    }

    public void setTipoInformacion(TipoInformacionExpediente tipoInformacion) {
        this.tipoInformacion = tipoInformacion;
    }
}
