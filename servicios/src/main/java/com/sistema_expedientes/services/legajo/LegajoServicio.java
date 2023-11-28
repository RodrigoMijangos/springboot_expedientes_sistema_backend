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
    public Legajo get(LegajoCompositeKey id) throws Exception {
        Optional<Legajo> in_bd = this.repositorio.findById(id);

        return in_bd.orElseThrow(Exception::new);
    }

    @Override
    public List<Legajo> getLegajosExpediente(ExpedienteCompositeKey id) {
        return null;
    }

    @Override
    public Legajo create(CreateLegajoRequestDTO request) throws Exception {
        return null;
    }

    public Legajo create(LegajoRequestDTO request, Expediente expediente) throws Exception {
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
            this.googleDriveService.deleteFileFromId(this.get(request).getGoogleDriveFolderId());
            repositorio.deleteById(request);
        }else throw new Exception();
    }

    @Override
    public Legajo guardarListaDocumentos(ListaDocumentosLegajoRequestDTO request, MultipartFile[] files) throws Exception {
        Legajo in_bd = this.get(request.getLegajo());
        Queue<MultipartFile> toSave = new LinkedList<>(List.of(files));

        in_bd.setDocumentos(
               this.documentoServicio.createList(
                    request.getDocumentos()
                            .stream()
                            .map(documentoRequest -> {
                                try {
                                    return this.documentoServicio.create(documentoRequest, toSave.remove(), in_bd.getGoogleDriveFolderId());

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .collect(Collectors.toList())
                )
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

    public Legajo guardarDocumento(CreateDocumentInsideLegajoRequestDTO request, MultipartFile file) throws Exception {
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
