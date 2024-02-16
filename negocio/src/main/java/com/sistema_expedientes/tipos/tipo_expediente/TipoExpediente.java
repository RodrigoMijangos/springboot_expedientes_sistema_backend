package com.sistema_expedientes.tipos.tipo_expediente;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema_expedientes.expediente.Expediente;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipos_expediente")
public class TipoExpediente {

    @Id
    @Column(name = "identificador")
    private Short identificador;
    @Column(name = "tipo_expediente")
    private String TipoExpediente;
    @Column(name = "vigente")
    @JsonIgnore
    private Boolean vigente;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoExpediente")
    @JsonBackReference
    private List<Expediente> expedientes;

    public TipoExpediente() {
    }

    public TipoExpediente(Short identificador, String tipoExpediente, Boolean vigente, String descripcion) {
        this.identificador = identificador;
        TipoExpediente = tipoExpediente;
        this.vigente = vigente;
        this.descripcion = descripcion;
    }

    public Short getIdentificador() {
        return identificador;
    }

    public String getTipoExpediente() {
        return TipoExpediente;
    }

    public void setTipoExpediente(String tipoExpediente) {
        TipoExpediente = tipoExpediente;
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
