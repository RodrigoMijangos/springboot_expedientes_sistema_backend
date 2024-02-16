package com.sistema_expedientes.serie_documental;

import com.fasterxml.jackson.annotation.*;
import com.sistema_expedientes.tecnica_seleccion.CatalagoTecnicaSeleccion;
import com.sistema_expedientes.serie_documental.seccion.Seccion;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "series_documentales")
public class SerieDocumental{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short identificador;
    private String clave;
    private String nombre;
    @Column(name = "valor_documental_administrativo")
    private Boolean valorDocumentalAdministrativo;
    @Column(name = "valor_documental_legal")
    private Boolean valorDocumentalLegal;
    @Column(name = "valor_documental_contable")
    private Boolean valorDocumentalContable;
    @Column(name = "periodos_conservacion_tramite")
    private Short periodosEnTramite;
    @Column(name = "periodos_conservacion_concentracion")
    private Short periodosEnConcentracion;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "tecnica_seleccion")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "identificador")
    private CatalagoTecnicaSeleccion tecnicaSeleccion;
    @Column(name = "vigente")
    @JsonIgnore
    private Boolean vigente;
    private String observaciones;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "seccion")
    @Nullable
    @JsonBackReference
    private Seccion seccion;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_documental_padre")
    @Nullable
    @JsonBackReference
    private SerieDocumental seriePadre;
    @OneToMany(mappedBy = "seriePadre")
    @JsonManagedReference
    private Set<SerieDocumental> subseries;

    public SerieDocumental() {
    }

    public SerieDocumental(Short identificador, String clave, String nombre, Boolean valorDocumentalAdministrativo, Boolean valorDocumentalLegal, Boolean valorDocumentalContable, Short periodosEnTramite, Short periodosEnConcentracion, CatalagoTecnicaSeleccion tecnicaSeleccion, Boolean vigente, String observaciones, @Nullable Seccion seccion, @Nullable SerieDocumental seriePadre, Set<SerieDocumental> subseries) {
        this.identificador = identificador;
        this.clave = clave;
        this.nombre = nombre;
        this.valorDocumentalAdministrativo = valorDocumentalAdministrativo;
        this.valorDocumentalLegal = valorDocumentalLegal;
        this.valorDocumentalContable = valorDocumentalContable;
        this.periodosEnTramite = periodosEnTramite;
        this.periodosEnConcentracion = periodosEnConcentracion;
        this.tecnicaSeleccion = tecnicaSeleccion;
        this.vigente = vigente;
        this.observaciones = observaciones;
        this.seccion = seccion;
        this.seriePadre = seriePadre;
        this.subseries = subseries;
    }

    public Short getIdentificador() {
        return identificador;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public CatalagoTecnicaSeleccion getTecnicaSeleccion() {
        return tecnicaSeleccion;
    }

    public void setTecnicaSeleccion(CatalagoTecnicaSeleccion tecnicaSeleccion) {
        this.tecnicaSeleccion = tecnicaSeleccion;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Nullable
    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(@Nullable Seccion seccion) {
        this.seccion = seccion;
    }

    @Nullable
    public SerieDocumental getSeriePadre() {
        return seriePadre;
    }

    public void setSeriePadre(@Nullable SerieDocumental seriePadre) {
        this.seriePadre = seriePadre;
    }

    public Set<SerieDocumental> getSubseries() {
        return subseries;
    }

    public void setSubseries(Set<SerieDocumental> subseries) {
        this.subseries = subseries;
    }
}