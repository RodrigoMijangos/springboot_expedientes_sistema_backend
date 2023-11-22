package com.sistema_expedientes.documento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_expedientes.legajo.Legajo;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "documentos")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;
    private String nombre;
    @Column(name = "google_drive_file_id")
    @JsonIgnore
    private String googleDriveFileId;
    @Column(name = "url_web_view")
    private String urlWebView;
    @Column(name = "fecha_creacion")
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_edicion")
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime fechaEdicion;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "contenido_legajo",
            joinColumns = {
                    @JoinColumn(name = "identificador_documento", referencedColumnName = "identificador")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "serie_documental_expediente", referencedColumnName = "serie_documental_expediente"),
                    @JoinColumn(name = "unidad_administrativa_generadora_expediente", referencedColumnName = "unidad_administrativa_generadora_expediente"),
                    @JoinColumn(name = "numero_expediente", referencedColumnName = "numero_expediente"),
                    @JoinColumn(name = "fecha_apertura_expediente", referencedColumnName = "fecha_apertura_expediente"),
                    @JoinColumn(name = "numero_legajo", referencedColumnName = "numero_legajo")
            }
    )
    @JsonBackReference
    private List<Legajo> legajo;

    public Documento() {
    }

    public Documento(Long identificador, String nombre, String googleDriveFileId, String urlWebView, LocalDateTime fechaCreacion, LocalDateTime fechaEdicion, List<Legajo> legajo) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.googleDriveFileId = googleDriveFileId;
        this.urlWebView = urlWebView;
        this.fechaCreacion = fechaCreacion;
        this.fechaEdicion = fechaEdicion;
        this.legajo = legajo;
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

    public String getGoogleDriveFileId() {
        return googleDriveFileId;
    }

    public void setGoogleDriveFileId(String googleDriveFileId) {
        this.googleDriveFileId = googleDriveFileId;
    }

    public String getUrlWebView() {
        return urlWebView;
    }

    public void setUrlWebView(String urlWebView) {
        this.urlWebView = urlWebView;
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

    public List<Legajo> getLegajo() {
        return legajo;
    }

    public void setLegajo(List<Legajo> legajo) {
        this.legajo = legajo;
    }
}