package com.sistema_expedientes.entities;

import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Legajo {

    @EmbeddedId
    private LegajoCompositeKey id;

    private Byte numeroLegajo;



}
