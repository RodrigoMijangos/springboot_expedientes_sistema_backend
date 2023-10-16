package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.SerieDocumental;
import com.sistema_expedientes.entities.dto.request.SerieDocumentalRequestDTO;
import com.sistema_expedientes.repositories.SerieDocumentalRepositorio;
import com.sistema_expedientes.services.interfaces.ISerieDocumentalRepositorioDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieDocumentalServicio implements ISerieDocumentalRepositorioDTO {

    @Autowired
    private SerieDocumentalRepositorio repositorio;

    @Autowired
    private ModelMapper mapper;

    @Override
    public SerieDocumental get(Short id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<SerieDocumental> list() {
        return repositorio.findAll();
    }

    public List<SerieDocumental> list_hijos(Short id){

        Optional<SerieDocumental> in_db = repositorio.findById(id);

        return in_db.map(serieDocumental -> repositorio.findAllBySeriePadre(serieDocumental)).orElse(null);

    }

    @Override
    public SerieDocumental create(SerieDocumentalRequestDTO request) {
        Optional<SerieDocumental> in_bd = repositorio.findById(request.getId());
        if(in_bd.isEmpty()){
            SerieDocumental to_db = dtoToEntity(request);

            if(request.getSerie_padre() != null){
                Optional<SerieDocumental> serie_padre = repositorio.findById(request.getSerie_padre());
                if(serie_padre.isPresent()){
                    to_db.setSeriePadre(serie_padre.get());
                }else{
                    return null;
                }
            }

            return repositorio.save(to_db);

        }

        return null;
    }

    @Override
    public SerieDocumental put(Short id, SerieDocumentalRequestDTO request) {
        return null;
    }

    private SerieDocumental dtoToEntity(SerieDocumentalRequestDTO dto){
        TypeMap<SerieDocumentalRequestDTO, SerieDocumental> propertyManager = this.mapper.createTypeMap(SerieDocumentalRequestDTO.class, SerieDocumental.class);
        propertyManager.addMapping(SerieDocumentalRequestDTO::getEn_tramite, SerieDocumental::setEnTramite);
        propertyManager.addMapping(SerieDocumentalRequestDTO::getEn_concentracion, SerieDocumental::setEnConcentracion);
        propertyManager.addMapping(SerieDocumentalRequestDTO::getProcedimiento_final, SerieDocumental::setProcedimientoFinal);

        return this.mapper.map(dto, SerieDocumental.class);
    }

}