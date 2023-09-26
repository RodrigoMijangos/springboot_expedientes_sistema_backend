package com.sistema_expedientes.entities.dto.request;

public class UnidadAdministrativaRequestDTO extends _DTO{

    private final Byte id;
    private final String clave;
    private final String nombre;

    public UnidadAdministrativaRequestDTO(Byte id, String clave, String nombre) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public Byte getId() {
        return id;
    }

    public String getClave() {
        return this.clave;
    }

    public String getNombre() {
        return this.nombre;
    }

}
