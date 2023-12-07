package com.sistema_expedientes.services.legajo;

import com.sistema_expedientes.documento.Documento;
import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.google.drive_main.service.GoogleDriveService;
import com.sistema_expedientes.legajo.dto.request.base.LegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.CreateDocumentInsideLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.ListaDocumentosLegajoRequestDTO;
import com.sistema_expedientes.legajo.Legajo;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import com.sistema_expedientes.legajo.composite_key.LegajoCompositeKey;
import com.sistema_expedientes.legajo.dto.request.specific.CreateLegajoRequestDTO;
import com.sistema_expedientes.legajo.dto.request.specific.PUTLegajoRequestDTO;
import com.sistema_expedientes.legajo.repository.LegajoRepositorio;
import com.sistema_expedientes.services.documento.DocumentoServicio;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import com.sistema_expedientes.services.formatter.FolderInstanceNameFormatter;
import com.sistema_expedientes.services.legajo.mapeo.MapeoLegajoServicio;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LegajoServicio implements LegajoServicioMetodos {

    private final LegajoRepositorio repositorio;
    private final DocumentoServicio documentoServicio;
    private final GoogleDriveService googleDriveService;
    private final FolderInstanceNameFormatter folderInstanceNameFormatter;
    private final MapeoLegajoServicio mapeoServicio;

    public LegajoServicio(LegajoRepositorio repositorio, DocumentoServicio documentoServicio, GoogleDriveService googleDriveService, FolderInstanceNameFormatter folderInstanceNameFormatter, MapeoLegajoServicio mapeoServicio) {
        this.repositorio = repositorio;
        this.documentoServicio = documentoServicio;
        this.googleDriveService = googleDriveService;
        this.folderInstanceNameFormatter = folderInstanceNameFormatter;
        this.mapeoServicio = mapeoServicio;
    }

    @Override
    public Legajo get(LegajoCompositeKey id) throws ResourceNotFoundException {
        return this.repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                new Throwable("El legajo no está disponible o no existe")
        ));
    }

    @Override
    public List<Legajo> getLegajosExpediente(ExpedienteCompositeKey id) {
        return null;
    }

    @Override
    public Legajo create(CreateLegajoRequestDTO request) throws Exception {
        return null;
    }

    public Legajo create(LegajoRequestDTO request, Expediente expediente) throws ResourceNotFoundException, IOException {
        Legajo to_save = this.mapeoServicio.legajoRequestToEntity(request);
        to_save.setId(this.iniciarOAgregarNumeroLegajo(expediente));
        to_save.setGoogleDriveFolderId(
                this.googleDriveService.createFolder(
                        this.folderInstanceNameFormatter.setGoogleDriveFolderName(to_save, expediente),
                        expediente.getGoogleDriveFolderId()
                )
        );
        return this.repositorio.save(to_save);
    }

    @Override
    public Legajo put(PUTLegajoRequestDTO request) throws ResourceNotFoundException {

        Legajo in_bd = this.get(request.getId());
        Legajo to_bd = this.mapeoServicio.legajoRequestToEntity(request);

        to_bd.setGoogleDriveFolderId(in_bd.getGoogleDriveFolderId());
        to_bd.setDocumentos(in_bd.getDocumentos());

        return this.repositorio.save(to_bd);
    }

    @Override
    public void delete(LegajoCompositeKey request) throws ResourceNotFoundException, IOException {
        if(registroEstaPresente(request)){
            this.googleDriveService.deleteFileFromId(this.get(request).getGoogleDriveFolderId());
            repositorio.deleteById(request);
        }
    }

    @Override
    public Legajo guardarListaDocumentos(ListaDocumentosLegajoRequestDTO request, MultipartFile[] files) throws ResourceNotFoundException {
        Queue<MultipartFile> toSave = new LinkedList<>(List.of(files));
        Legajo in_bd = this.get(request.getLegajo());

        in_bd.getDocumentos().addAll(
                request.getDocumentos()
                        .stream()
                        .map(documentoRequest -> {
                            try {
                                return this.documentoServicio.create(
                                        documentoRequest,
                                        toSave.remove(),
                                        in_bd.getGoogleDriveFolderId()
                                );
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList()
        );

        return this.repositorio.save(in_bd);
    }

    /*public static void main(String[] args) {

        List<String> numbers = Arrays.asList("UNO", "DOS", "TRES", "CUATRO");

        Queue<String> queue =  new LinkedList<>(numbers);
        Stack<String> stack = new Stack<>();
        stack.addAll(numbers);

        System.out.println("Cola comportamiento desde lista\n");

        while (!queue.isEmpty())
            System.out.println(queue.remove() + "\n");

        System.out.println("Pila comportamiento desde lista\n");

        while (!stack.empty())
            System.out.println(stack.pop() + "\n");

    }*/

    public Legajo guardarDocumento(CreateDocumentInsideLegajoRequestDTO request, MultipartFile file) throws ResourceNotFoundException, IOException {
        Legajo in_bd = this.get(request.getLegajo());
        Documento to_save = this.documentoServicio.create(request.getDocumento(), file, in_bd.getGoogleDriveFolderId());
        in_bd.getDocumentos().add(to_save);
        return this.repositorio.save(in_bd);

    }

    private LegajoCompositeKey iniciarOAgregarNumeroLegajo(Expediente expediente){
        Short numeroLegajo = (short) (repositorio.getNumeroLegajo(
                        expediente.getIdentificadorSerieDocumental(),
                        expediente.getUnidadAdministrativaGeneradora(),
                        expediente.getNumeroExpediente(),
                        expediente.getFechaApertura()
                ).orElse((short) 0) + 1);

        return new LegajoCompositeKey(
                expediente.getIdentificadorSerieDocumental(),
                expediente.getUnidadAdministrativaGeneradora(),
                expediente.getNumeroExpediente(),
                expediente.getFechaApertura(),
                numeroLegajo
                );
    }

    private boolean registroEstaPresente(LegajoCompositeKey id){
        return repositorio.existsById(id);
    }



}
