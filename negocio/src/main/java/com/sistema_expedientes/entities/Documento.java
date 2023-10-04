package com.sistema_expedientes.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    public Documento() {
    }

    public Documento(BigInteger id, String nombre, Byte hojas) {
        this.id = id;
        this.nombre = nombre;
        this.hojas = hojas;
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
}
