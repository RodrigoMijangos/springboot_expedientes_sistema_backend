package com.sistema_expedientes.services.legajo.mapeo;

import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.base.LegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.PUTLegajoRequestDTO;
import com.sistema_expedientes.services.expediente.ExpedienteServicio;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MapeoLegajoServicio {

    private final ModelMapper mapper;

    public MapeoLegajoServicio(ModelMapper mapper){
        this.mapper = mapper;
    }

    public Legajo legajoRequestToEntity(LegajoRequestDTO dto){
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<LegajoRequestDTO, Legajo> legajoRequestDTOLegajoTypeMap = obtenerOCrearMapeoLegajoRequest();

        if(dto instanceof PUTLegajoRequestDTO)
            return obtenerOCrearMapeoPUTLegajoRequest(legajoRequestDTOLegajoTypeMap, (PUTLegajoRequestDTO) dto);
        else
            return legajoRequestDTOLegajoTypeMap.map(dto);
    }

    public TypeMap<LegajoRequestDTO, Legajo> obtenerOCrearMapeoLegajoRequest(){
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

    /*public Legajo obtenerOCrearMapeoCreateLegajoRequest(
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
    }*/

    public Legajo obtenerOCrearMapeoPUTLegajoRequest(
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
    
}
