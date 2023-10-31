package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.*;
import com.sistema_expedientes.entities.enumerates.CondicionAccesoExpediente;
import com.sistema_expedientes.entities.enumerates.FormatoExpediente;
import com.sistema_expedientes.entities.enumerates.TipoInformacionExpediente;
import com.sistema_expedientes.entities.enumerates.TradicionDocumentalExpediente;
import com.sistema_expedientes.repositories.ExpedienteRepositorio;
import com.sistema_expedientes.services.interfaces.IExpedienteServicio;
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

    @Override
    public Expediente create(CreateExpedienteRequestDTO request) {
        LocalDate today = LocalDate.now();
        LocalDate dateFilter1 = LocalDate.of(today.getYear(), 1, 1);
        LocalDate dateFilter2 = LocalDate.of(today.getYear(), 12, 31);

        Optional<Short> to_check = repositorio.numeroExpedienteMasProximo(request.getUnidadAdministrativaGeneradora(), request.getIdentificadorSerieDocumental(), dateFilter1, dateFilter2);

        Expediente to_bd = dtoToEntityExpediente(request);

        to_bd.setNumeroExpediente((short) (to_check.orElse((short)0) + 1));
        to_bd.setFechaApertura(today);

        return repositorio.save(to_bd);
    }

    @Override
    public Expediente put(PUTExpedienteRequestDTO request) throws Exception {
        if (this.registroEstaPresente(request.getId()))
            return repositorio.save(dtoToEntityExpediente(request));
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

    private Expediente dtoToEntityExpediente(_DTO dto) {
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        if (dto instanceof ExpedienteRequest) {

            crearSiNoExisteMapeoExpedienteRequest();

            if (dto instanceof CreateExpedienteRequestDTO) {

                crearSiNoExisteMapeoCreateExpedienteRequest();

                return this.mapper.map(dto, Expediente.class);
            }else if (dto instanceof PUTExpedienteRequestDTO) {

                crearSiNoExisteMapeoPUTExpedienteRequest();

                return this.mapper.map(dto, Expediente.class);
            }
        }

        return new Expediente();

    }

    private void crearSiNoExisteMapeoExpedienteRequest(){
        if (this.mapper.getTypeMap(ExpedienteRequest.class, Expediente.class) == null) {
            TypeMap<ExpedienteRequest, Expediente> expedienteTypeMap = this.mapper.createTypeMap(ExpedienteRequest.class, Expediente.class);

            Converter<Byte, FormatoExpediente> toFormatoExpediente = ctx -> ctx.getSource() == null ? null : FormatoExpediente.valueOf(ctx.getSource());
            Converter<Byte, CondicionAccesoExpediente> toCondicionAcceso = ctx -> ctx.getSource() == null ? null : CondicionAccesoExpediente.valueOf(ctx.getSource());
            Converter<Byte, TradicionDocumentalExpediente> toTradicionDocumental = ctx -> ctx.getSource() == null ? null : TradicionDocumentalExpediente.valueOf(ctx.getSource());
            Converter<Byte, TipoInformacionExpediente> toTipoInformacion = ctx -> ctx.getSource() == null ? null : TipoInformacionExpediente.valueOf(ctx.getSource());

            expedienteTypeMap.addMappings(mapping -> {
                mapping.map(ExpedienteRequest::getNumeroProyecto, Expediente::setNumeroProyecto);
                mapping.map(ExpedienteRequest::getNumeroContacto, Expediente::setNumeroContacto);
                mapping.using(toFormatoExpediente).map(ExpedienteRequest::getFormatoExpediente, Expediente::setFormatoExpediente);
                mapping.using(toCondicionAcceso).map(ExpedienteRequest::getCondicionAcceso, Expediente::setCondicionAcceso);
                mapping.using(toTradicionDocumental).map(ExpedienteRequest::getTradicionDocumental, Expediente::setTradicionDocumental);
                mapping.using(toTipoInformacion).map(ExpedienteRequest::getTipoInformacion, Expediente::setTipoInformacion);
            });

        }
    }

    private void crearSiNoExisteMapeoCreateExpedienteRequest(){
        if(this.mapper.getTypeMap(CreateExpedienteRequestDTO.class, Expediente.class) == null){
            TypeMap<CreateExpedienteRequestDTO, Expediente> createTypeMap = this.mapper.createTypeMap(CreateExpedienteRequestDTO.class, Expediente.class);
            createTypeMap.addMappings(mapping -> {
                mapping.map(CreateExpedienteRequestDTO::getIdentificadorSerieDocumental, Expediente::setIdentificadorSerieDocumental);
                mapping.map(CreateExpedienteRequestDTO::getUnidadAdministrativaGeneradora, Expediente::setUnidadAdministrativaGeneradora);
            }).includeBase(ExpedienteRequest.class, Expediente.class);
        }
    }

    private void crearSiNoExisteMapeoPUTExpedienteRequest(){
        if (this.mapper.getTypeMap(PUTExpedienteRequestDTO.class, Expediente.class) == null) {
            TypeMap<PUTExpedienteRequestDTO, Expediente> typeMap = mapper.createTypeMap(PUTExpedienteRequestDTO.class, Expediente.class);
            typeMap.addMappings(mapping -> {
                mapping.map(requestDTO -> requestDTO.getId().getUnidadAdministrativaGeneradora(), Expediente::setUnidadAdministrativaGeneradora);
                mapping.map(requestDTO -> requestDTO.getId().getIdentificadorSerieDocumental(), Expediente::setIdentificadorSerieDocumental);
                mapping.map(requestDTO -> requestDTO.getId().getNumeroExpediente(), Expediente::setNumeroExpediente);
                mapping.map(requestDTO -> requestDTO.getId().getFechaApertura(), Expediente::setFechaApertura);
            });
            typeMap.includeBase(ExpedienteRequest.class, Expediente.class);
        }
    }

}