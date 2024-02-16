package com.sistema_expedientes.tipos.tipo_documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDocumentoRepositorio extends JpaRepository<TipoDocumento, Short> {

    List<TipoDocumento> findAllByVigenteIsTrue();

    Optional<TipoDocumento> findByIdentificadorAndVigenteIsTrue(Short identificador);

    @Query("UPDATE TipoDocumento tp SET tp.vigente = false WHERE tp.identificador = :identificador AND tp.vigente = true")
    void setTipoDocumentoNoVigente(Short identificador);

    @Query("UPDATE TipoDocumento tp SET tp.vigente = true WHERE tp.identificador = :identificador AND tp.vigente = false")
    void setTipoDocumentoVigente(Short identificador);

    @Query("SELECT TipoDocumento.vigente FROM TipoDocumento tp WHERE tp.identificador = :identificador")
    Optional<Boolean> isTipoDocumentoVigente();
}
