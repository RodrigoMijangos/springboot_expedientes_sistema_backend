package com.sistema_expedientes.entities.compositesKeys;

import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

import java.io.Serializable;

@Embeddable
@Table(name = "")
public class LegajoCompositeKey implements Serializable {

    @AttributeOverrides({})
    private ExpedienteCompositeKey expedienteId;

    @Column(name = "")
    private Long documentoId;

}
