package com.sistema_expedientes.entities.dto.request;

public class UnidadAdministrativaRequestDTO extends _DTO{

    private String clave;
    private String nombre;
    private String unidadPrincipal;
    private String piso;
    private String extensionTelefonica;

    public UnidadAdministrativaRequestDTO() {
    }

    public UnidadAdministrativaRequestDTO(String clave, String nombre, String unidadPrincipal, String piso, String extensionTelefonica) {
        this.clave = clave;
        this.nombre = nombre;
        this.unidadPrincipal = unidadPrincipal;
        this.piso = piso;
        this.extensionTelefonica = extensionTelefonica;
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

    public String getUnidadPrincipal() {
        return unidadPrincipal;
    }

    public void setUnidadPrincipal(String unidadPrincipal) {
        this.unidadPrincipal = unidadPrincipal;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getExtensionTelefonica() {
        return extensionTelefonica;
    }

    public void setExtensionTelefonica(String extensionTelefonica) {
        this.extensionTelefonica = extensionTelefonica;
    }
}
