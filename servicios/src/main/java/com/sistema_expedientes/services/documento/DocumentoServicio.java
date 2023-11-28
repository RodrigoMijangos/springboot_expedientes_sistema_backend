package com.sistema_expedientes.services.documento;

import com.sistema_expedientes.documento.Documento;
import com.sistema_expedientes.documento.dto.request.base.DocumentoRequest;
import com.sistema_expedientes.google.drive_main.service.GoogleDriveService;
import com.sistema_expedientes.documento.repository.DocumentoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DocumentoServicio implements DocumentoServicioMetodos {

    private final DocumentoRepositorio repositorio;
    private final GoogleDriveService googleDriveService;

    public DocumentoServicio(DocumentoRepositorio repositorio, GoogleDriveService googleDriveService) {
        this.repositorio = repositorio;
        this.googleDriveService = googleDriveService;
    }

    @Override
    public List<Documento> getAll() {
        return this.repositorio.findAll();
    }

    @Override
    public Documento get(Long id) throws Exception {
        return this.repositorio.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Documento create(DocumentoRequest request, MultipartFile file, String folderId) throws IOException {

        Map<String, String> responseMapping = this.googleDriveService.saveFile(file, folderId);

        Documento to_bd = new Documento();
        to_bd.setNombre(request.getNombre());
        to_bd.setGoogleDriveFileId(responseMapping.get("id"));
        to_bd.setUrlWebView(responseMapping.get("shareViewLink"));

        return this.repositorio.save(to_bd);
    }

    @Override
    public Documento put(DocumentoRequest request, Long id) throws Exception {
        Documento in_bd = this.get(id);
        in_bd.setNombre(request.getNombre());
        return this.repositorio.save(in_bd);
    }

    @Override
    public void delete(Long id) throws Exception {
        Documento in_bd = this.get(id);
        if(this.googleDriveService.deleteFileFromId(in_bd.getGoogleDriveFileId()))
            this.repositorio.deleteById(id);
        else throw new Exception();
    }

    public List<Documento> createList(List<Documento> request){
        return this.repositorio.saveAll(request);
    }

    private boolean registroEstaPresente(Long id){
        return this.repositorio.existsById(id);
    }

}
