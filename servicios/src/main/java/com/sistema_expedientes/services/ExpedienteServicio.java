package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.dto.request.ExpedienteRequestDTO;
import com.sistema_expedientes.services.interfaces.IExpedienteServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpedienteServicio implements IExpedienteServicio {
    @Override
    public Expediente get() {
        return null;
    }

    @Override
    public List<Expediente> list() {
        return null;
    }

    @Override
    public List<Expediente> search(ExpedienteRequestDTO request) {
        return null;
    }

    @Override
    public Expediente create(ExpedienteRequestDTO request) {
        return null;
    }

    @Override
    public Expediente put(ExpedienteRequestDTO search, ExpedienteRequestDTO request) {
        return null;
    }
}
