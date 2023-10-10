package com.sistema_expedientes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    private BigInteger id;
    @NotNull
    private String nombre;
    @NotNull
    private Byte hojas;
    @NotNull
    @NotEmpty
    private String url;

    public Documento() {
    }

    public Documento(BigInteger id, String nombre, Byte hojas, String url) {
        this.id = id;
        this.nombre = nombre;
        this.hojas = hojas;
        this.url = url;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Byte getHojas() {
        return hojas;
    }

    public void setHojas(Byte hojas) {
        this.hojas = hojas;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
