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
    @Column(name = "numero_mueble")
    private String numeroMueble;
    @Column(name = "letraEstante")
    private String letraEstante;
    @Column(name = "numero_pasillo")
    private String numeroPasillo;
    @Column(name = "letraBateria")
    private String letraBateria;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumns({
            @JoinColumn(name = "serie_documental_expediente", referencedColumnName = "serie_documental", updatable = false, insertable = false),
            @JoinColumn(name = "unidad_administrativa_generadora_expediente", referencedColumnName = "unidad_administrativa_generadora", insertable = false, updatable = false),
            @JoinColumn(name = "numero_expediente", referencedColumnName = "numero_expediente", updatable = false, insertable = false),
            @JoinColumn(name = "fecha_apertura_expediente", referencedColumnName = "fecha_apertura", insertable = false, updatable = false)
    })
    @JsonBackReference
    private Expediente expediente;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH},
            mappedBy = "legajo"
    )
    @JsonManagedReference
    private Set<Documento> documentos;

    public Legajo() {
    }

    public Legajo(LegajoCompositeKey id, String numeroMueble, String letraEstante, String numeroPasillo, String letraBateria, Expediente expediente, Set<Documento> documentos) {
        this.id = id;
        this.numeroMueble = numeroMueble;
        this.letraEstante = letraEstante;
        this.numeroPasillo = numeroPasillo;
        this.letraBateria = letraBateria;
        this.expediente = expediente;
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