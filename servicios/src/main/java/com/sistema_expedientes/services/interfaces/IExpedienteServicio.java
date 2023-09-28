package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.ExpedienteRequestDTO;

import java.util.List;

public interface IExpedienteServicio {

    public Expediente get(ExpedienteCompositeKey key);

    public List<Expediente> list();

    public List<Expediente> search(ExpedienteRequestDTO request);

    public Expediente create(ExpedienteRequestDTO request);

    public Expediente put(ExpedienteCompositeKey search, ExpedienteRequestDTO request);

}
