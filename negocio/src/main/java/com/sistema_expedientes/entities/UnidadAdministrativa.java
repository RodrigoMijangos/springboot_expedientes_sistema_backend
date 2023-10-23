package com.sistema_expedientes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "unidades_administrativas")
public class UnidadAdministrativa {

    @Id
    private String clave;
    private String nombre;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_principal")
    @Nullable
    @JsonBackReference
    private UnidadAdministrativa unidadPrincipal;
    private String piso;
    @Column(name = "extension_telefonica")
    private String extensionTelefonica;
    @OneToMany(mappedBy = "unidadPrincipal")
    @JsonManagedReference
    private Set<UnidadAdministrativa> unidadesGeneradoras;

    public UnidadAdministrativa() {
    }

    public UnidadAdministrativa(String clave, String nombre, @Nullable UnidadAdministrativa unidadPrincipal, String piso, String extensionTelefonica, Set<UnidadAdministrativa> unidadesGeneradoras) {
        this.clave = clave;
        this.nombre = nombre;
        this.unidadPrincipal = unidadPrincipal;
        this.piso = piso;
        this.extensionTelefonica = extensionTelefonica;
        this.unidadesGeneradoras = unidadesGeneradoras;
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
    public UnidadAdministrativa getUnidadPrincipal() {
        return unidadPrincipal;
    }

    public void setUnidadPrincipal(@Nullable UnidadAdministrativa unidadPrincipal) {
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
