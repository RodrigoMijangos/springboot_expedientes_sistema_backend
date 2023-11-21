package com.sistema_expedientes.services.expediente;

import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.expediente.dto.request.specific.CreateExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.specific.PUTExpedienteRequestDTO;

import java.util.List;

public interface ExpedienteServicioMetodos {

    public Expediente get(ExpedienteCompositeKey key) throws Exception;

    public List<Expediente> list();

    public List<Expediente> search(CreateExpedienteRequestDTO request);

    public Expediente create(CreateExpedienteRequestDTO request) throws Exception;

    public Expediente put(PUTExpedienteRequestDTO request) throws Exception;

}
