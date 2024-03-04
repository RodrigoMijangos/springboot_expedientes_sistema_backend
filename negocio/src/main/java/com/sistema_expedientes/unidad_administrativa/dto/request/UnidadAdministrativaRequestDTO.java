package com.sistema_expedientes.unidad_administrativa.dto.request;

import com.sistema_expedientes.base.request._DTO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

public class UnidadAdministrativaRequestDTO extends _DTO {

    @Size(min = 1, max = 5)
    @NotNull
    private String clave;
    @NotBlank
    private String nombre;
    @Nullable
    @Size(min = 1, max = 5)
    private String unidadPrincipal;
    @NotBlank
    @Size(min = 1, max = 2)
    @Pattern(regexp = "[0-9]", message = "Solo puede usar numeros")
    private String piso;

    @NotBlank
    @Size(min = 1, max = 5)
    @Pattern(regexp = "[0-9]*", message = "Solo puede usar numeros")
    private String extensionTelefonica;

    public UnidadAdministrativaRequestDTO() {
    }

    public UnidadAdministrativaRequestDTO(String clave, String nombre, @Nullable String unidadPrincipal, @NumberFormat String piso, @NumberFormat String extensionTelefonica) {
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
