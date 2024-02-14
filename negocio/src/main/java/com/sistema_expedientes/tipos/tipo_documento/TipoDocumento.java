package com.sistema_expedientes.tipos.tipo_documento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_expedientes.documento.Documento;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipos_documento")
public class TipoDocumento {

    @Id
    @Column(name = "identificador")
    private Short identificador;
    @Column(name = "tipo_documento")
    private String TipoDocumento;
    @Column(name = "vigente")
    @JsonIgnore
    private Boolean vigente;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoDocumento")
    @JsonBackReference
    private List<Documento> documentos;

    public TipoDocumento() {
    }

    public TipoDocumento(Short identificador, String tipoDocumento, Boolean vigente, String descripcion) {
        this.identificador = identificador;
        this.TipoDocumento = tipoDocumento;
        this.vigente = vigente;
        this.descripcion = descripcion;
    }

    public Short getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Short identificador) {
        this.identificador = identificador;
    }

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        TipoDocumento = tipoDocumento;
    }

    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
