package com.sistema_expedientes.services.documento;

import com.sistema_expedientes.entities.Documento;
import com.sistema_expedientes.entities.dto.request.DocumentoRequest;
import com.sistema_expedientes.repositories.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DocumentoServicio implements DocumentoServicioMetodos {

    @Autowired
    private DocumentoRepositorio repositorio;

    @Override
    public List<Documento> getAll() {
        return this.repositorio.findAll();
    }

    @Override
    public Documento get(Long id) throws Exception {
        return this.repositorio.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Documento create(DocumentoRequest request) {
        Documento to_bd = new Documento();
        to_bd.setNombre(request.getNombre());
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
        if(registroEstaPresente(id))
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
