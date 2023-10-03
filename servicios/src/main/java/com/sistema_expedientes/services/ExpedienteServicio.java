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

        Short new_identificador_numerico;

        Optional<Expediente> to_check =
                repositorio.findFirstByUnidadAdministrativaAndPeriodoAperturaOrderByIdentificadorNumericoDesc
                        (request.getUnidad_administrativa(), request.getPeriodo_apertura());

        new_identificador_numerico = to_check.map(expediente -> (short) (expediente.getIdentificadorNumerico() + 1)).orElse((short) 1);

        Expediente to_bd = new Expediente(new_identificador_numerico, request.getPeriodo_apertura(), request.getUnidad_administrativa(), request.getSerie_documental(), request.getPeriodo_cierre());

        return repositorio.save(to_bd);

    }

    @Override
    public Expediente put(ExpedienteCompositeKey search, ExpedienteRequestDTO request) {
        Optional<Expediente> in_bd = repositorio.findById(search);

        if (in_bd.isPresent()){
            Expediente to_bd = in_bd.get();
            to_bd.setPeriodoCierre(request.getPeriodo_cierre());
            return repositorio.save(to_bd);
        }

        return null;

    }
}
