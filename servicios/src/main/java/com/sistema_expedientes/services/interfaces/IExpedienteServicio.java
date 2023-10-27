package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.CreateExpedienteRequestDTO;
import com.sistema_expedientes.entities.dto.request.PUTExpedienteRequestDTO;

import java.util.List;
import java.util.Optional;

public interface IExpedienteServicio {

    public Optional<Expediente> get(ExpedienteCompositeKey key);

    public List<Expediente> list();

    public List<Expediente> search(CreateExpedienteRequestDTO request);

    public Expediente create(CreateExpedienteRequestDTO request);

    public Expediente put(PUTExpedienteRequestDTO request);

}
