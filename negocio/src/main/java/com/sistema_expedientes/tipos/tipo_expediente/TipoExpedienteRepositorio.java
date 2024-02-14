package com.sistema_expedientes.tipos.tipo_expediente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoExpedienteRepositorio extends JpaRepository<TipoExpediente, Short> {

    List<TipoExpediente> findAllByVigenteIsTrue();

    Optional<TipoExpediente> findByIdentificadorAndVigenteIsTrue(Short identificador);

    @Query("UPDATE TipoExpediente te SET te.vigente = false WHERE te.identificador = :identificador AND te.vigente = true")
    void setTipoExpedienteNoVigente(Short identificador);

    @Query("UPDATE TipoExpediente te SET te.vigente = true WHERE te.identificador = :identificador AND te.vigente = false")
    void setTipoExpedienteVigente(Short identificador);

    @Query("SELECT TipoExpediente.vigente FROM TipoExpediente te WHERE te.identificador = :identificador")
    Optional<Boolean> isTipoExpedienteVigente(Short identificador);

}
