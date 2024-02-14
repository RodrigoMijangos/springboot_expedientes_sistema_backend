package com.sistema_expedientes.serie_documental.seccion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistema_expedientes.serie_documental.SerieDocumental;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;

@Entity
@Table(name = "secciones_docuemntales")
public class Seccion {

    @Id
    @NotBlank
    @Column(name = "clave")
    private String clave;
    @NotBlank
    @Column(name = "nombre", unique = true)
    private String nombre;
    @Nullable
    private String descripcion;
    @Column(name = "vigente")
    @JsonIgnore
    private Boolean vigente;
    @OneToMany(mappedBy = "seccion")
    @JsonManagedReference
    private Set<SerieDocumental> series;

    public Seccion() {
    }

    public Seccion(String clave, String nombre, @Nullable String descripcion, Boolean vigente, Set<SerieDocumental> series) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.vigente = vigente;
        this.series = series;
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

    @Nullable
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@Nullable String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean isVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public Set<SerieDocumental> getSeries() {
        return series;
    }

    public void setSeries(Set<SerieDocumental> series) {
        this.series = series;
    }
}
