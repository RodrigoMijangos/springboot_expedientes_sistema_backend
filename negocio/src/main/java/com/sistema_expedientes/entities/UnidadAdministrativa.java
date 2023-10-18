package com.sistema_expedientes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "unidades_administrativas")
public class UnidadAdministrativa {

    @Id
    @NotBlank
    private String clave;
    @NotBlank
    private String nombre;
    @NotBlank
    private String piso;
    @Column(name = "extension_telefonica")
    private String extensionTelefonica;
    @OneToMany(mappedBy = "unidadPrincipal")
    @JsonManagedReference
    private Set<UnidadAdministrativa> unidadesGeneradoras;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_principal", referencedColumnName = "clave")
    @JsonBackReference
    private UnidadAdministrativa unidadPrincipal;

    public UnidadAdministrativa(){}

    public UnidadAdministrativa(String clave, String nombre, String piso, String extensionTelefonica, Set<UnidadAdministrativa> unidadesGeneradoras, UnidadAdministrativa unidadPrincipal) {
        this.clave = clave;
        this.nombre = nombre;
        this.piso = piso;
        this.extensionTelefonica = extensionTelefonica;
        this.unidadesGeneradoras = unidadesGeneradoras;
        this.unidadPrincipal = unidadPrincipal;
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

    public Set<UnidadAdministrativa> getUnidadesGeneradoras() {
        return unidadesGeneradoras;
    }

    public void setUnidadesGeneradoras(Set<UnidadAdministrativa> unidadesGeneradoras) {
        this.unidadesGeneradoras = unidadesGeneradoras;
    }

    public UnidadAdministrativa getUnidadPrincipal() {
        return unidadPrincipal;
    }

    public void setUnidadPrincipal(UnidadAdministrativa unidadPrincipal) {
        this.unidadPrincipal = unidadPrincipal;
    }
}
