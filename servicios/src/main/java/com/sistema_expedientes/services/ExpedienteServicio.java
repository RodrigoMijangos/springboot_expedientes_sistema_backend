package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.converters.FormatoExpedienteConverter;
import com.sistema_expedientes.entities.converters.TipoInformacionExpedienteConverter;
import com.sistema_expedientes.entities.dto.request.ExpedienteRequestDTO;
import com.sistema_expedientes.entities.enumerates.CondicionAccesoExpediente;
import com.sistema_expedientes.entities.enumerates.FormatoExpediente;
import com.sistema_expedientes.entities.enumerates.TipoInformacionExpediente;
import com.sistema_expedientes.entities.enumerates.TradicionDocumentalExpediente;
import com.sistema_expedientes.repositories.ExpedienteRepositorio;
import com.sistema_expedientes.services.interfaces.IExpedienteServicio;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpedienteServicio implements IExpedienteServicio {

    @Autowired
    private ExpedienteRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

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
        LocalDate today = LocalDate.now();
        LocalDate dateFilter1 = LocalDate.of(today.getYear(), 1, 1);
        LocalDate dateFilter2 = LocalDate.of(today.getYear(), 12, 31);

        Optional<Short> to_check = repositorio.numeroExpedienteMasProximo(request.getUnidadAdministrativaGeneradora(), request.getIdentificadorSerieDocumental(), dateFilter1, dateFilter2);

        Expediente to_bd = dtoToEntity(request);

        to_bd.setNumeroExpediente(to_check.map(aShort -> (short) (aShort + 1)).orElseGet(() -> (short) 1));
        to_bd.setFechaApertura(today);
        to_bd.setTipoInformacion(TipoInformacionExpediente.valueOf(request.getTipoInformacion()));
        to_bd.setTradicionDocumental(TradicionDocumentalExpediente.valueOf(request.getTipoInformacion()));
        to_bd.setFormatoExpediente(FormatoExpediente.valueOf(request.getFormatoExpediente()));
        to_bd.setCondicionAcceso(CondicionAccesoExpediente.valueOf(request.getCondicionAcceso()));

        return repositorio.save(to_bd);
    }

    @Override
    public Expediente put(ExpedienteCompositeKey search, ExpedienteRequestDTO request) {
        return null;
    }

    private Expediente dtoToEntity(ExpedienteRequestDTO dto){

        mapper.getConfiguration().setAmbiguityIgnored(true);
        return this.mapper.map(dto, Expediente.class);

    }

}