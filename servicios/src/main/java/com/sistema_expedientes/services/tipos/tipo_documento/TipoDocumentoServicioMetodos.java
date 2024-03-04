package com.sistema_expedientes.services.tipos.tipo_documento;

import com.sistema_expedientes.tipos.tipo_documento.TipoDocumento;

import java.util.List;
import java.util.Optional;

public interface TipoDocumentoServicioMetodos {

    List<TipoDocumento> obtenerDocumentosVigentes();

    Optional<TipoDocumento> buscarPorId(Short identificador);

    void eliminarDocumento(Short identificador);

    void activarDocumento(Short identificador);

    Optional<Boolean> verificarVigencia(Short identificador);
}
