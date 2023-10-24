package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.CreateExpedienteRequestDTO;
import com.sistema_expedientes.entities.dto.request.ExpedienteCompositeKeyRequestDTO;
import com.sistema_expedientes.entities.dto.request.PUTExpedienteRequestDTO;
import com.sistema_expedientes.entities.dto.request._DTO;
import com.sistema_expedientes.entities.enumerates.CondicionAccesoExpediente;
import com.sistema_expedientes.entities.enumerates.FormatoExpediente;
import com.sistema_expedientes.entities.enumerates.TipoInformacionExpediente;
import com.sistema_expedientes.entities.enumerates.TradicionDocumentalExpediente;
import com.sistema_expedientes.repositories.ExpedienteRepositorio;
import com.sistema_expedientes.services.interfaces.IExpedienteServicio;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Expediente> search(CreateExpedienteRequestDTO request) {
        return null;
    }

    @Override
    public Expediente create(@NotNull CreateExpedienteRequestDTO request) {
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
    public Expediente put(PUTExpedienteRequestDTO request) {

        Optional<Expediente> to_check = this.get(request.getId());

        if(to_check.isPresent()){
            return repositorio.save(dtoToEntity(request));
        }
        else return null;
    }

    private Expediente dtoToEntity(_DTO dto){

        Converter<Byte, FormatoExpediente> toFormatoExpediente = ctx -> ctx.getSource() == null ? null : FormatoExpediente.valueOf(ctx.getSource());
        Converter<Byte, CondicionAccesoExpediente> toCondicionAcceso = ctx -> ctx.getSource() == null ? null : CondicionAccesoExpediente.valueOf(ctx.getSource());
        Converter<Byte, TradicionDocumentalExpediente> toTradicionDocumental = ctx -> ctx.getSource() == null ? null : TradicionDocumentalExpediente.valueOf(ctx.getSource());
        Converter<Byte, TipoInformacionExpediente> toTipoInformacion = ctx -> ctx.getSource() == null ? null : TipoInformacionExpediente.valueOf(ctx.getSource());

        if(dto instanceof CreateExpedienteRequestDTO){
            mapper.getConfiguration().setAmbiguityIgnored(true);
            return this.mapper.map(dto, Expediente.class);
        }
        if(dto instanceof PUTExpedienteRequestDTO){
            this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            TypeMap<PUTExpedienteRequestDTO, Expediente> typeMap = mapper.createTypeMap(PUTExpedienteRequestDTO.class, Expediente.class);
            typeMap.addMappings(mapping -> {
                mapping.map(requestDTO -> requestDTO.getId().getUnidadAdministrativaGeneradora(), Expediente::setUnidadAdministrativaGeneradora);
                mapping.map(requestDTO -> requestDTO.getId().getIdentificadorSerieDocumental(), Expediente::setIdentificadorSerieDocumental);
                mapping.map(requestDTO -> requestDTO.getId().getNumeroExpediente(), (destino, valor) -> destino.setNumeroExpediente((Short)valor));
                mapping.map(requestDTO -> requestDTO.getId().getFechaApertura(), Expediente::setFechaApertura);
                mapping.map(PUTExpedienteRequestDTO::getNumeroProyecto, Expediente::setNumeroProyecto);
                mapping.map(PUTExpedienteRequestDTO::getNumeroContacto, Expediente::setNumeroContacto);
                mapping.using(toFormatoExpediente).map(PUTExpedienteRequestDTO::getFormatoExpediente, Expediente::setFormatoExpediente);
                mapping.using(toCondicionAcceso).map(PUTExpedienteRequestDTO::getCondicionAcceso, Expediente::setCondicionAcceso);
                mapping.using(toTradicionDocumental).map(PUTExpedienteRequestDTO::getTradicionDocumental, Expediente::setTradicionDocumental);
                mapping.using(toTipoInformacion).map(PUTExpedienteRequestDTO::getTipoInformacion, Expediente::setTipoInformacion);
            });


            return this.mapper.map(dto, Expediente.class);
        }

        return null;

    }

}