package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.CreateExpedienteRequestDTO;
import com.sistema_expedientes.entities.dto.request.PUTExpedienteRequestDTO;

import java.util.List;

public interface IExpedienteServicio {

    public Expediente get(ExpedienteCompositeKey key) throws Exception;

    public List<Expediente> list();

    public List<Expediente> search(CreateExpedienteRequestDTO request);

    public Expediente create(CreateExpedienteRequestDTO request);

    public Expediente put(PUTExpedienteRequestDTO request) throws Exception;

}
