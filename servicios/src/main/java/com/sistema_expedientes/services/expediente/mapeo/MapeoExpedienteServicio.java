package com.sistema_expedientes.services.expediente.mapeo;

import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.expediente.dto.request.specific.CreateExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.base.ExpedienteRequest;
import com.sistema_expedientes.expediente.dto.request.specific.PUTExpedienteRequestDTO;
import com.sistema_expedientes.base.request._DTO;
import com.sistema_expedientes.expediente.enumerates.CondicionAccesoExpediente;
import com.sistema_expedientes.expediente.enumerates.FormatoExpediente;
import com.sistema_expedientes.expediente.enumerates.TipoInformacionExpediente;
import com.sistema_expedientes.expediente.enumerates.TradicionDocumentalExpediente;
import com.sistema_expedientes.google.drive_main.service.GoogleDriveService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class MapeoExpedienteServicio {

    private final ModelMapper mapper;
    private final GoogleDriveService googleDriveService;

    public MapeoExpedienteServicio(ModelMapper mapper, GoogleDriveService googleDriveService) {
        this.mapper = mapper;
        this.googleDriveService = googleDriveService;
    }

    public Expediente dtoToEntityExpediente(_DTO dto) {
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
                mapping.skip(Expediente::setGoogleDriveFolderId);
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
