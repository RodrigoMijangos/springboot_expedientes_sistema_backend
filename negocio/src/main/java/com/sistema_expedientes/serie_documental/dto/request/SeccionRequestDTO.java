package com.sistema_expedientes.serie_documental.dto.request;

import com.sistema_expedientes.base.request._DTO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

public class SeccionRequestDTO extends _DTO {

    @NotBlank(message = "La clave es obligatoria")
    private String clave;
    @NotBlank(message = "El nombre es obligatorio")
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
