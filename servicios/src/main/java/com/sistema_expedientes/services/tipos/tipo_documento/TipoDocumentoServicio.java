package com.sistema_expedientes.services.tipos.tipo_documento;

import com.sistema_expedientes.tipos.tipo_documento.TipoDocumento;
import com.sistema_expedientes.tipos.tipo_documento.TipoDocumentoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoServicio implements TipoDocumentoServicioMetodos{

    private final TipoDocumentoRepositorio repositorio;

    public TipoDocumentoServicio(TipoDocumentoRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public List<TipoDocumento> obtenerDocumentosVigentes() {
        return repositorio.findAllByVigenteIsTrue();
    }

    @Override
    public Optional<TipoDocumento> buscarPorId(Short identificador) {
        return repositorio.findByIdentificadorAndVigenteIsTrue(identificador);
    }

    @Override
    public void eliminarDocumento(Short identificador) {
        repositorio.setTipoDocumentoNoVigente(identificador);
    }

    @Override
    public void activarDocumento(Short identificador) {
        repositorio.setTipoDocumentoVigente(identificador);
    }

    @Override
    public Optional<Boolean> verificarVigencia(Short identificador) {
        return repositorio.findByVigenteTrue(identificador);
    }
}
