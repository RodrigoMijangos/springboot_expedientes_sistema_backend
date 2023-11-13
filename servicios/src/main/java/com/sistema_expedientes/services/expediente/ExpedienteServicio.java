package com.sistema_expedientes.services.expediente;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.*;
import com.sistema_expedientes.repositories.ExpedienteRepositorio;
import com.sistema_expedientes.services.expediente.mapeo.MapeoExpedienteServicio;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import com.sistema_expedientes.services.legajo.mapeo.MapeoLegajoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpedienteServicio implements ExpedienteServicioMetodos {

    @Autowired
    private ExpedienteRepositorio repositorio;

    @Autowired
    private MapeoExpedienteServicio mapeoServicio;

    private MapeoLegajoServicio mapeoLegajoServicio;

    @Autowired
    private LegajoServicio legajoServicio;

    @Override
    public Expediente get(ExpedienteCompositeKey key) throws Exception {
        Optional<Expediente> in_bd = repositorio.findById(key);

        return in_bd.orElseThrow(Exception::new);

    }

    @Override
    public List<Expediente> list() {
        return repositorio.findAll();
    }

    @Override
    public List<Expediente> search(CreateExpedienteRequestDTO request) {
        return null;
    }

    public Expediente guardarListaLegajos(ListaLegajosExpedienteRequestDTO request) throws Exception {
        Expediente in_bd = this.get(request.getExpediente());

        in_bd.setLegajos(
                new HashSet<>(this.legajoServicio.createList(request.getLegajos()))
        );

        return this.repositorio.save(in_bd);

    }

    @Override
    public Expediente create(CreateExpedienteRequestDTO request) {
        LocalDate today = LocalDate.now();
        LocalDate dateFilter1 = LocalDate.of(today.getYear(), 1, 1);
        LocalDate dateFilter2 = LocalDate.of(today.getYear(), 12, 31);

        Optional<Short> to_check = repositorio.numeroExpedienteMasProximo(request.getUnidadAdministrativaGeneradora(), request.getIdentificadorSerieDocumental(), dateFilter1, dateFilter2);

        Expediente to_bd = mapeoServicio.dtoToEntityExpediente(request);

        to_bd.setNumeroExpediente((short) (to_check.orElse((short)0) + 1));
        to_bd.setFechaApertura(today);

        return repositorio.save(to_bd);
    }

    @Override
    public Expediente put(PUTExpedienteRequestDTO request) throws Exception {
        if (this.registroEstaPresente(request.getId()))
            return repositorio.save(mapeoServicio.dtoToEntityExpediente(request));
        else throw new Exception();
    }

    public void delete(ExpedienteCompositeKey id) throws Exception {
        if(this.registroEstaPresente(id)){
            this.repositorio.deleteById(id);
        }else throw new Exception();
    }

    private boolean registroEstaPresente(ExpedienteCompositeKey id){
        return this.repositorio.existsById(id);
    }

}