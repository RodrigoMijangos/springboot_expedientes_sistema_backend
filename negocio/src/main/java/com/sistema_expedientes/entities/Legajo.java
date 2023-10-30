package com.sistema_expedientes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "legajos")
public class Legajo {

    @EmbeddedId
    private LegajoCompositeKey id;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "serie_documental_expediente", referencedColumnName = "serie_documental"),
            @JoinColumn(name = "unidad_administrativa_generadora_expediente", referencedColumnName = "unidad_administrativa_generadora"),
            @JoinColumn(name = "numero_expediente", referencedColumnName = "numero_expediente"),
            @JoinColumn(name = "fecha_apertura_expediente", referencedColumnName = "fecha_apertura")
    })
    @JsonBackReference
    private Expediente expediente;
    @Column(name = "numero_mueble")
    private String numeroMueble;
    @Column(name = "letraEstante")
    private String letraEstante;
    @Column(name = "numero_pasillo")
    private String numeroPasillo;
    @Column(name = "letraBateria")
    private String letraBateria;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "contenido_legajo",
            joinColumns = {
                    @JoinColumn(name = "serie_documental_expediente", referencedColumnName = "serie_documental"),
                    @JoinColumn(name = "unidad_administrativa_generadora_expediente", referencedColumnName = "unidad_administrativa_generadora"),
                    @JoinColumn(name = "numero_expediente", referencedColumnName = "numero_expediente"),
                    @JoinColumn(name = "fecha_apertura_expediente", referencedColumnName = "fecha_apertura")
            },
            inverseJoinColumns = @JoinColumn(name = "identificador_documento", referencedColumnName = "identificador")
    )
    @JsonManagedReference
    private Set<Documento> documentos;

    public Legajo() {
    }

    public Legajo(LegajoCompositeKey id, Expediente expediente, String numeroMueble, String letraEstante, String numeroPasillo, String letraBateria, Set<Documento> documentos) {
        this.id = id;
        this.expediente = expediente;
        this.numeroMueble = numeroMueble;
        this.letraEstante = letraEstante;
        this.numeroPasillo = numeroPasillo;
        this.letraBateria = letraBateria;
        this.documentos = documentos;
    }

    public LegajoCompositeKey getId() {
        return id;
    }

    public void setId(LegajoCompositeKey id) {
        this.id = id;
    }

    public String getNumeroMueble() {
        return numeroMueble;
    }

    public void setNumeroMueble(String numeroMueble) {
        this.numeroMueble = numeroMueble;
    }

    public String getLetraEstante() {
        return letraEstante;
    }

    public void setLetraEstante(String letraEstante) {
        this.letraEstante = letraEstante;
    }

    public String getNumeroPasillo() {
        return numeroPasillo;
    }

    public void setNumeroPasillo(String numeroPasillo) {
        this.numeroPasillo = numeroPasillo;
    }

    public String getLetraBateria() {
        return letraBateria;
    }

    public void setLetraBateria(String letraBateria) {
        this.letraBateria = letraBateria;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }
}