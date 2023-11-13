package com.sistema_expedientes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import jakarta.persistence.*;

import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "contenido_legajo",
            joinColumns = {
                    @JoinColumn(name = "serie_documental_expediente", referencedColumnName = "serie_documental_expediente"),
                    @JoinColumn(name = "unidad_administrativa_generadora_expediente", referencedColumnName = "unidad_administrativa_generadora_expediente"),
                    @JoinColumn(name = "numero_expediente", referencedColumnName = "numero_expediente"),
                    @JoinColumn(name = "fecha_apertura_expediente", referencedColumnName = "fecha_apertura_expediente"),
                    @JoinColumn(name = "numero_legajo", referencedColumnName = "numero_legajo")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "identificador_documento", referencedColumnName = "identificador")
            }
    )
    @JsonManagedReference
    private List<Documento> documentos;

    public Legajo() {
    }

    public Legajo(LegajoCompositeKey id, String numeroMueble, String letraEstante, String numeroPasillo, String letraBateria, Expediente expediente, List<Documento> documentos) {
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

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
}