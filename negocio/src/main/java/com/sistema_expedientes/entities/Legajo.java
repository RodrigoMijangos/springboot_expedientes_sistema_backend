package com.sistema_expedientes.entities;

import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import jakarta.persistence.*;

@Entity
@Table(name = "legajos")
public class Legajo {

    @EmbeddedId
    private LegajoCompositeKey id;

    @Column(name = "legajo")
    private Byte numeroLegajo;

    public Legajo() {
    }

    public LegajoCompositeKey getId() {
        return id;
    }

    public void setId(LegajoCompositeKey id) {
        this.id = id;
    }

    public Byte getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(Byte numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

}
