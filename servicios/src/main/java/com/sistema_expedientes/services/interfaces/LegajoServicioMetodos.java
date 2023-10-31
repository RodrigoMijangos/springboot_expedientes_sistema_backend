package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import com.sistema_expedientes.entities.dto.request.LegajoRequestDTO;

import java.util.List;

public interface LegajoServicioMetodos {

    public List<Legajo> getLegajosExpediente(ExpedienteCompositeKey expedienteCompositeKey);
    public Legajo create(LegajoRequestDTO request);
    public Legajo put(LegajoRequestDTO request);
    public void delete(LegajoCompositeKey id);

}
