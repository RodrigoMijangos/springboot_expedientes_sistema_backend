package com.sistema_expedientes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "documentos")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;
    private String nombre;
    private String url;
    @Column(name = "fecha_creacion")
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_edicion")
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime fechaEdicion;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "documentos")
    @JsonBackReference
    private Set<Legajo> legajos;

    public Documento() {
    }

    public Documento(Long identificador, String nombre, String url, LocalDateTime fechaCreacion, LocalDateTime fechaEdicion, Set<Legajo> legajos) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.url = url;
        this.fechaCreacion = fechaCreacion;
        this.fechaEdicion = fechaEdicion;
        this.legajos = legajos;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(LocalDateTime fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public Set<Legajo> getLegajos() {
        return legajos;
    }

    public void setLegajos(Set<Legajo> legajos) {
        this.legajos = legajos;
    }
}