package com.sistema_expedientes.expediente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.expediente.enumerates.converters.CondicionAccesoExpedienteConverter;
import com.sistema_expedientes.expediente.enumerates.converters.FormatoExpedienteConverter;
import com.sistema_expedientes.expediente.enumerates.converters.TipoInformacionExpedienteConverter;
import com.sistema_expedientes.expediente.enumerates.converters.TradicionDocumentalExpedienteConverter;
import com.sistema_expedientes.expediente.enumerates.CondicionAccesoExpediente;
import com.sistema_expedientes.expediente.enumerates.FormatoExpediente;
import com.sistema_expedientes.expediente.enumerates.TipoInformacionExpediente;
import com.sistema_expedientes.expediente.enumerates.TradicionDocumentalExpediente;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.tipos.tipo_expediente.TipoExpediente;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

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
    @Column(name = "periodo_cierre")
    private Short periodoCierre;
    private String asunto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_expediente", referencedColumnName = "identificador")
    private TipoExpediente tipoExpediente;
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
    @Column(name = "google_drive_folder_id")
    @JsonIgnore
    private String googleDriveFolderId;
    @OneToMany(mappedBy = "expediente")
    @JsonManagedReference
    private Set<Legajo> legajos;

    public Expediente() {
    }

    public Expediente(Short identificadorSerieDocumental, String unidadAdministrativaGeneradora, Short numeroExpediente, LocalDate fechaApertura, Short periodoCierre, String asunto, TipoExpediente tipoExpediente, String numeroProyecto, String nombreProyecto, String acronimoInstitucion, String nombreInstitucion, String numeroContacto, Short cantidadHojas, FormatoExpediente formatoExpediente, CondicionAccesoExpediente condicionAcceso, TradicionDocumentalExpediente tradicionDocumental, TipoInformacionExpediente tipoInformacion, String googleDriveFolderId, Set<Legajo> legajos) {
        this.identificadorSerieDocumental = identificadorSerieDocumental;
        this.unidadAdministrativaGeneradora = unidadAdministrativaGeneradora;
        this.numeroExpediente = numeroExpediente;
        this.fechaApertura = fechaApertura;
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
        this.googleDriveFolderId = googleDriveFolderId;
        this.legajos = legajos;
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

    public TipoExpediente getTipoExpediente() {
        return tipoExpediente;
    }

    public void setTipoExpediente(TipoExpediente tipoExpediente) {
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

    public String getGoogleDriveFolderId() {
        return googleDriveFolderId;
    }

    public void setGoogleDriveFolderId(String googleDriveFolderId) {
        this.googleDriveFolderId = googleDriveFolderId;
    }

    public Set<Legajo> getLegajos() {
        return legajos;
    }

    public void setLegajos(Set<Legajo> legajos) {
        this.legajos = legajos;
    }
}
