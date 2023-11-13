package com.sistema_expedientes.services.legajo;

import com.sistema_expedientes.entities.Documento;
import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import com.sistema_expedientes.entities.dto.request.*;
import com.sistema_expedientes.repositories.LegajoRepositorio;
import com.sistema_expedientes.services.documento.DocumentoServicio;
import com.sistema_expedientes.services.documento.mapeo.MapeoDocumentoServicio;
import com.sistema_expedientes.services.legajo.mapeo.MapeoLegajoServicio;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LegajoServicio implements LegajoServicioMetodos{

    @Autowired
    private LegajoRepositorio repositorio;

    @Autowired
    private DocumentoServicio documentoServicio;

    @Autowired
    private MapeoLegajoServicio mapeoServicio;

    @Autowired
    private MapeoDocumentoServicio mapeoDocumentoServicio;

    @Override
    public Legajo get(LegajoCompositeKey id) throws Exception{
        Optional<Legajo> in_bd = this.repositorio.findById(id);

        return in_bd.orElseThrow(Exception::new);
    }

    @Override
    public List<Legajo> getLegajosExpediente(ExpedienteCompositeKey id) {
        return null;
    }

    @Override
    public Legajo create(CreateLegajoRequestDTO request) throws Exception {
        Legajo to_save = this.mapeoServicio.legajoRequestToEntity(request);
        to_save.getId().setNumeroLegajo(this.iniciarOAgregarNumeroLegajo(request.getExpediente()));

        return this.repositorio.save(to_save);
    }

    @Override
    public Legajo put(PUTLegajoRequestDTO request) throws Exception {
        if(registroEstaPresente(request.getId())){
            Legajo to_bd = this.mapeoServicio.legajoRequestToEntity(request);
            return this.repositorio.save(to_bd);
        }

        throw new Exception("No se pudo guardar");
    }

    @Override
    public void delete(LegajoCompositeKey request) throws Exception{
        if(registroEstaPresente(request)){
            repositorio.deleteById(request);
        }else throw new Exception();
    }

    public List<Legajo> createList(List<CreateLegajoRequestDTO> request){
        return this.repositorio.saveAll(
                request.stream()
                        .map(legajo -> {
                            try {
                                return this.mapeoServicio.legajoRequestToEntity(legajo);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).toList()
        );
    }

    @Override
    public Legajo guardarListaDocumentos(ListaDocumentosLegajoRequestDTO request) throws Exception {
        Legajo in_bd = this.get(request.getLegajo());
        in_bd.setDocumentos(
               this.documentoServicio.createList(
                    request.getDocumentos().stream()
                        .map(documentoRequest -> this.mapeoDocumentoServicio.dtoToEntity(documentoRequest))
                        .collect(Collectors.toList())
                )
        );

        return this.repositorio.save(in_bd);
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
