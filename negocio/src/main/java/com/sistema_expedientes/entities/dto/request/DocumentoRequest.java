package com.sistema_expedientes.entities.dto.request;

public class DocumentoRequest extends _DTO{
    private String nombre;

    public DocumentoRequest() {
    }

    public DocumentoRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
