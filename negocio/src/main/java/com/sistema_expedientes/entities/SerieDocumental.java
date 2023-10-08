package com.sistema_expedientes.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Set;

@Entity
@Table(name = "series_documentales")
public class SerieDocumental {

    @Id
    @Max(value = 999)
    @Min(value = 1)
    private Short id;

    @Column(unique = true)
    @NotBlank
    @NotNull
    @NotEmpty
    private String nombre;

    @Column(unique = true)
    @NotBlank
    @NotNull
    @NotEmpty
    private String serie;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "serie_padre", referencedColumnName = "id", table = "series_documentales")
    @Nullable
    private SerieDocumental seriePadre;

    @NotNull
    private Boolean valor_documental_legal;

    @NotNull
    private Boolean valor_documental_administrativo;

    @NotNull
    private Boolean valor_documental_contable;

    @Max(value = 20)
    @Min(value = 1)
    @NotNull
    private Byte enTramite;

    @Max(value = 20)
    @Min(value = 1)
    @NotNull
    private Byte enConcentracion;

    @NotNull
    private Byte procedimientoFinal;

    @Nullable
    private String observaciones;

    @OneToMany(mappedBy = "seriePadre")
    private Set<SerieDocumental> subseries;

    public SerieDocumental() {
    }

    public SerieDocumental(Short id, String nombre, String serie, @Nullable SerieDocumental seriePadre, Boolean valor_documental_legal, Boolean valor_documental_administrativo, Boolean valor_documental_contable, Byte enTramite, Byte enConcentracion, Byte procedimientoFinal, @Nullable String observaciones, Set<SerieDocumental> subseries) {
        this.id = id;
        this.nombre = nombre;
        this.serie = serie;
        this.seriePadre = seriePadre;
        this.valor_documental_legal = valor_documental_legal;
        this.valor_documental_administrativo = valor_documental_administrativo;
        this.valor_documental_contable = valor_documental_contable;
        this.enTramite = enTramite;
        this.enConcentracion = enConcentracion;
        this.procedimientoFinal = procedimientoFinal;
        this.observaciones = observaciones;
        this.subseries = subseries;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Nullable
    public SerieDocumental getSeriePadre() {
        return seriePadre;
    }

    public void setSeriePadre(@Nullable SerieDocumental seriePadre) {
        this.seriePadre = seriePadre;
    }

    public Boolean getValor_documental_legal() {
        return valor_documental_legal;
    }

    public void setValor_documental_legal(Boolean valor_documental_legal) {
        this.valor_documental_legal = valor_documental_legal;
    }

    public Boolean getValor_documental_administrativo() {
        return valor_documental_administrativo;
    }

    public void setValor_documental_administrativo(Boolean valor_documental_administrativo) {
        this.valor_documental_administrativo = valor_documental_administrativo;
    }

    public Boolean getValor_documental_contable() {
        return valor_documental_contable;
    }

    public void setValor_documental_contable(Boolean valor_documental_contable) {
        this.valor_documental_contable = valor_documental_contable;
    }

    public Byte getEnTramite() {
        return enTramite;
    }

    public void setEnTramite(Byte enTramite) {
        this.enTramite = enTramite;
    }

    public Byte getEnConcentracion() {
        return enConcentracion;
    }

    public void setEnConcentracion(Byte enConcentracion) {
        this.enConcentracion = enConcentracion;
    }

    public Byte getProcedimientoFinal() {
        return procedimientoFinal;
    }

    public void setProcedimientoFinal(Byte procedimientoFinal) {
        this.procedimientoFinal = procedimientoFinal;
    }

    @Nullable
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(@Nullable String observaciones) {
        this.observaciones = observaciones;
    }

    public Set<SerieDocumental> getSubseries() {
        return subseries;
    }

    public void setSubseries(Set<SerieDocumental> subseries) {
        this.subseries = subseries;
    }
}
