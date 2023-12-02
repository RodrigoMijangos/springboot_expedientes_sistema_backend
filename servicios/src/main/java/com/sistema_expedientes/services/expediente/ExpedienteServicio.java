package com.sistema_expedientes.services.expediente;

import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.expediente.dto.request.specific.CreateExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.specific.PUTExpedienteRequestDTO;
import com.sistema_expedientes.expediente.dto.request.specific.ListaLegajosExpedienteRequestDTO;
import com.sistema_expedientes.expediente.repository.ExpedienteRepositorio;
import com.sistema_expedientes.google.drive_main.service.GoogleDriveService;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.services.expediente.mapeo.MapeoExpedienteServicio;
import com.sistema_expedientes.services.formatter.FolderInstanceNameFormatter;
import com.sistema_expedientes.services.legajo.LegajoServicio;
import com.sistema_expedientes.services.serie_documental.SerieDocumentalServicio;
import com.sistema_expedientes.services.unidad_administrativa.UnidadAdministrativaServicio;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExpedienteServicio implements ExpedienteServicioMetodos {

    private final ExpedienteRepositorio repositorio;
    private final MapeoExpedienteServicio mapeoServicio;
    private final LegajoServicio legajoServicio;
    private final UnidadAdministrativaServicio unidadAdministrativaServicio;
    private final FolderInstanceNameFormatter folderInstanceNameFormatter;
    private final GoogleDriveService googleDriveService;

    public ExpedienteServicio(
            ExpedienteRepositorio repositorio,
            MapeoExpedienteServicio mapeoServicio,
            LegajoServicio legajoServicio,
            UnidadAdministrativaServicio unidadAdministrativaServicio,
            FolderInstanceNameFormatter folderInstanceNameFormatter,
            GoogleDriveService googleDriveService
    ) {
        this.repositorio = repositorio;
        this.mapeoServicio = mapeoServicio;
        this.legajoServicio = legajoServicio;
        this.unidadAdministrativaServicio = unidadAdministrativaServicio;
        this.folderInstanceNameFormatter = folderInstanceNameFormatter;
        this.googleDriveService = googleDriveService;
    }

    @Override
    public Expediente get(ExpedienteCompositeKey key) throws ResourceNotFoundException {
        return repositorio.findById(key).orElseThrow(() -> new ResourceNotFoundException(
                new Throwable("El expediente que busca no se encuentra disponible o no existe")
        ));

    }

    @Override
    public List<Expediente> list() {
        return repositorio.findAll();
    }

    @Override
    public List<Expediente> search(CreateExpedienteRequestDTO request) {
        return null;
    }

    public Expediente guardarListaLegajos(ListaLegajosExpedienteRequestDTO request) throws ResourceNotFoundException, IOException {
        Expediente in_bd = this.get(request.getExpediente());

        Set<Legajo> to_save = new HashSet<>();

        for(CreateLegajoRequestDTO requestDTO : request.getLegajos()){
            to_save.add(
                    this.legajoServicio.create(requestDTO, in_bd)
            );
        }

        if(in_bd.getLegajos() != null)
            in_bd.getLegajos().addAll(to_save);
        else
            in_bd.setLegajos(to_save);

        return this.repositorio.save(in_bd);

    }

    @Override
    public Expediente create(CreateExpedienteRequestDTO request) throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate dateFilter1 = LocalDate.of(today.getYear(), 1, 1);
        LocalDate dateFilter2 = LocalDate.of(today.getYear(), 12, 31);

        Optional<Short> to_check = repositorio.numeroExpedienteMasProximo(request.getUnidadAdministrativaGeneradora(), request.getIdentificadorSerieDocumental(), dateFilter1, dateFilter2);

        Expediente to_bd = mapeoServicio.dtoToEntityExpediente(request);

        to_bd.setNumeroExpediente((short) (to_check.orElse((short)0) + 1));
        to_bd.setFechaApertura(today);

        UnidadAdministrativa unidadAdministrativa = this.unidadAdministrativaServicio.get(request.getUnidadAdministrativaGeneradora());

        to_bd.setGoogleDriveFolderId(this.googleDriveService.createFolder(
                this.folderInstanceNameFormatter.setGoogleDriveFolderName(to_bd),
                unidadAdministrativa.getGoogleDriveFolderId()
        ));

        return repositorio.save(to_bd);
    }

    @Override
    public Expediente put(PUTExpedienteRequestDTO request) throws Exception {
        Expediente in_bd = this.get(request.getId());
        Expediente mapped = mapeoServicio.dtoToEntityExpediente(request);
        mapped.setGoogleDriveFolderId(in_bd.getGoogleDriveFolderId());
        return repositorio.save(mapped);
    }

    public void delete(ExpedienteCompositeKey id) throws Exception {
        Expediente expediente = this.get(id);

        if(!expediente.getLegajos().isEmpty()){
            expediente.getLegajos().forEach(
                    legajo -> {
                        try {
                            this.legajoServicio.delete(legajo.getId());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }

        this.googleDriveService.deleteFileFromId(expediente.getGoogleDriveFolderId());

    }

    private boolean registroEstaPresente(ExpedienteCompositeKey id){
        return this.repositorio.existsById(id);
    }

}