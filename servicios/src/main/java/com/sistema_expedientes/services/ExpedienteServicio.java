package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.ExpedienteRequestDTO;
import com.sistema_expedientes.repositories.ExpedienteRepositorio;
import com.sistema_expedientes.services.interfaces.IExpedienteServicio;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedienteServicio implements IExpedienteServicio {

    @Autowired
    private ExpedienteRepositorio repositorio;

    @Override
    public Optional<Expediente> get(ExpedienteCompositeKey key) {
        return repositorio.findById(key);
    }

    @Override
    public List<Expediente> list() {
        return repositorio.findAll();
    }

    @Override
    public List<Expediente> search(ExpedienteRequestDTO request) {
        return null;
    }

    @Override
    public Expediente create(@NotNull ExpedienteRequestDTO request) {
        return null;
    }

    @Override
    public Expediente put(ExpedienteCompositeKey search, ExpedienteRequestDTO request) {
        return null;
    }
}
