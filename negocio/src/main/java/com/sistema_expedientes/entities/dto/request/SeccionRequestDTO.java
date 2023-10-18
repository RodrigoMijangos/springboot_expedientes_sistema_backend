package com.sistema_expedientes.entities.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

public class SeccionRequestDTO extends _DTO{

    @NotBlank(message = "La clave es obligatoria")
    @UniqueElements(message = "La clave debe ser única")
    private String clave;
    @NotBlank(message = "El nombre es obligatorio")
    @UniqueElements(message = "El nombre debe ser único")
    private String nombre;
    @Nullable
    private String descripcion;

    public SeccionRequestDTO() {
    }

    public SeccionRequestDTO(String clave, String nombre, @Nullable String descripcion) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
}
