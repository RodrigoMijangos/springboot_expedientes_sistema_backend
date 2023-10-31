package com.sistema_expedientes.services.interfaces;

import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.dto.request.LegajoRequestDTO;
import com.sistema_expedientes.repositories.LegajoRepositorio;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LegajoServicio {

    @Autowired
    private LegajoRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

    public Legajo create(LegajoRequestDTO request) throws Exception {
        Legajo to_save = legajoRequestToEntity(request);
        to_save.getId().setNumeroLegajo(this.iniciarOAgregarNumeroLegajo(request.getExpediente()));

        return this.repositorio.save(to_save);
    }

    private Legajo legajoRequestToEntity(LegajoRequestDTO dto) throws Exception {

        TypeMap<LegajoRequestDTO, Legajo> typeMap = this.mapper.getTypeMap(LegajoRequestDTO.class, Legajo.class);
        if(typeMap == null){
            this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            typeMap = this.mapper.createTypeMap(LegajoRequestDTO.class, Legajo.class);
            typeMap.addMappings(mapping -> {
                mapping.<Short>map(request -> request.getExpediente().getIdentificadorSerieDocumental(), (entity, valor) -> entity.getId().setIdentificadorSerieDocumental(valor));
                mapping.<String>map(request -> request.getExpediente().getUnidadAdministrativaGeneradora(), (entity, valor) -> entity.getId().setUnidadAdministrativaGeneradora(valor));
                mapping.<Short>map(request -> request.getExpediente().getNumeroExpediente(), (entity, valor) -> entity.getId().setNumeroExpediente(valor));
                mapping.<LocalDate>map(request -> request.getExpediente().getFechaApertura(), (entity, valor) -> entity.getId().setFechaApertura(valor));
                mapping.<Short>skip((legajo, o) -> legajo.getId().setNumeroLegajo(o));
                mapping.map(LegajoRequestDTO::getNumeroMueble, Legajo::setNumeroMueble);
                mapping.map(LegajoRequestDTO::getLetraEstante, Legajo::setLetraEstante);
                mapping.map(LegajoRequestDTO::getNumeroPasillo, Legajo::setNumeroPasillo);
                mapping.map(LegajoRequestDTO::getLetraBateria, Legajo::setLetraBateria);
            });

            return typeMap.map(dto);
        }

        throw new Exception("Error en Model Mapper");
    }

    private Short iniciarOAgregarNumeroLegajo(ExpedienteCompositeKey expediente){
        return (short) (repositorio.getNumeroLegajo(
                expediente.getIdentificadorSerieDocumental(),
                expediente.getUnidadAdministrativaGeneradora(),
                expediente.getNumeroExpediente(),
                expediente.getFechaApertura()
        ).orElse((short) 0) + 1);
    }

}
