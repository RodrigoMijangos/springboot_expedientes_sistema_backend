package com.sistema_expedientes.entities;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "unidades_administrativas")
public class UnidadAdministrativa {

    @Id
    @Max(value = 99)
    @Min(value = 1)
    private Byte id;

    @Size(max = 10)
    @NotBlank
    @Column(unique = true)
    private String clave;

    @NotBlank
    @Column(unique = true)
    private String nombre;

    public UnidadAdministrativa(){}

    public UnidadAdministrativa(Byte id, String clave, String nombre) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public UnidadAdministrativa(String clave, String nombre) {
        this.clave = clave;
        this.nombre = nombre;
    }
    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
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
}
