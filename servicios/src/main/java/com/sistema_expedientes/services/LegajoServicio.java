package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import com.sistema_expedientes.entities.dto.request.*;
import com.sistema_expedientes.repositories.LegajoRepositorio;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class LegajoServicio {

    @Autowired
    private LegajoRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

    public Legajo create(CreateLegajoRequestDTO request) throws Exception {
        Legajo to_save = legajoRequestToEntity(request);
        to_save.getId().setNumeroLegajo(this.iniciarOAgregarNumeroLegajo(request.getExpediente()));

        return this.repositorio.save(to_save);
    }

    public Legajo put(PUTLegajoRequestDTO request) throws Exception {
        if(registroEstaPresente(request.getId())){
            Legajo to_bd = legajoRequestToEntity(request);
            return this.repositorio.save(to_bd);
        }

        throw new Exception("No se pudo guardar");
    }

    public void delete(LegajoCompositeKey request) throws Exception{
        if(registroEstaPresente(request)){
            repositorio.deleteById(request);
        }else throw new Exception();
    }

    private Legajo legajoRequestToEntity(LegajoRequestDTO dto) throws Exception {
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<LegajoRequestDTO, Legajo> legajoRequestDTOLegajoTypeMap = obtenerOCrearMapeoLegajoRequest();

        if(dto instanceof CreateLegajoRequestDTO)
            return obtenerOCrearMapeoCreateLegajoRequest(legajoRequestDTOLegajoTypeMap, (CreateLegajoRequestDTO) dto);
        else if(dto instanceof PUTLegajoRequestDTO)
            return obtenerOCrearMapeoPUTLegajoRequest(legajoRequestDTOLegajoTypeMap, (PUTLegajoRequestDTO) dto);

        throw new Exception("Algo salió mal");
    }

    private TypeMap<LegajoRequestDTO, Legajo> obtenerOCrearMapeoLegajoRequest(){
        TypeMap<LegajoRequestDTO, Legajo> typeMap = this.mapper.getTypeMap(LegajoRequestDTO.class, Legajo.class);
        if(typeMap == null){
            return this.mapper.createTypeMap(LegajoRequestDTO.class, Legajo.class)
                    .addMappings(mapping -> {
                        mapping.map(LegajoRequestDTO::getNumeroMueble, Legajo::setNumeroMueble);
                        mapping.map(LegajoRequestDTO::getLetraEstante, Legajo::setLetraEstante);
                        mapping.map(LegajoRequestDTO::getNumeroPasillo, Legajo::setNumeroPasillo);
                        mapping.map(LegajoRequestDTO::getLetraBateria, Legajo::setLetraBateria);
                    });
        }

        return typeMap;
    }

    private Legajo obtenerOCrearMapeoCreateLegajoRequest(
            TypeMap<LegajoRequestDTO, Legajo> baseTypeMap,
            CreateLegajoRequestDTO dto
    ){
        TypeMap<CreateLegajoRequestDTO, Legajo> typeMap = this.mapper.getTypeMap(CreateLegajoRequestDTO.class, Legajo.class);
        if(typeMap == null){
            return this.mapper.createTypeMap(CreateLegajoRequestDTO.class, Legajo.class)
                    .addMappings(mapping -> {
                        mapping.<Short>map(
                                request -> request.getExpediente().getIdentificadorSerieDocumental(),
                                (entity, valor) -> entity.getId().setIdentificadorSerieDocumental(valor));
                        mapping.<String>map(
                                request -> request.getExpediente().getUnidadAdministrativaGeneradora(),
                                (entity, valor) -> entity.getId().setUnidadAdministrativaGeneradora(valor));
                        mapping.<Short>map(
                                request -> request.getExpediente().getNumeroExpediente(),
                                (entity, valor) -> entity.getId().setNumeroExpediente(valor));
                        mapping.<LocalDate>map(
                                request -> request.getExpediente().getFechaApertura(),
                                (entity, valor) -> entity.getId().setFechaApertura(valor));
                        mapping.<Short>skip((legajo, valor) -> legajo.getId().setNumeroLegajo(valor));
                    }).includeBase(baseTypeMap.getSourceType(), baseTypeMap.getDestinationType())
                    .map(dto);
        }

        return typeMap.map(dto);
    }

    private Legajo obtenerOCrearMapeoPUTLegajoRequest(
            TypeMap<LegajoRequestDTO, Legajo> baseTypeMap,
            PUTLegajoRequestDTO dto
    ){
        TypeMap<PUTLegajoRequestDTO, Legajo> typeMap = this.mapper.getTypeMap(PUTLegajoRequestDTO.class, Legajo.class);
        if(typeMap == null){
            return this.mapper.createTypeMap(PUTLegajoRequestDTO.class, Legajo.class)
                    .addMappings(mapping -> {
                        mapping.map(PUTLegajoRequestDTO::getId, Legajo::setId);
                    }).includeBase(baseTypeMap.getSourceType(), baseTypeMap.getDestinationType())
                    .map(dto);
        }

        return typeMap.map(dto);
    }

    private Short iniciarOAgregarNumeroLegajo(ExpedienteCompositeKey expediente){
        return (short) (repositorio.getNumeroLegajo(
                expediente.getIdentificadorSerieDocumental(),
                expediente.getUnidadAdministrativaGeneradora(),
                expediente.getNumeroExpediente(),
                expediente.getFechaApertura()
        ).orElse((short) 0) + 1);
    }

    private boolean registroEstaPresente(LegajoCompositeKey id){
        return repositorio.existsById(id);
    }

}
