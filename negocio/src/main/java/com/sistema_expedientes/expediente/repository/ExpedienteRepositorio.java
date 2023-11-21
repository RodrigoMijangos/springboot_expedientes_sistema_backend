package com.sistema_expedientes.expediente.repository;

import com.sistema_expedientes.expediente.Expediente;
import com.sistema_expedientes.expediente.composite_key.ExpedienteCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface ExpedienteRepositorio extends JpaRepository<Expediente, ExpedienteCompositeKey> {

    @Query(value = "SELECT e.numeroExpediente FROM Expediente e WHERE e.unidadAdministrativaGeneradora = :unidadAdministrativaGeneradora AND e.identificadorSerieDocumental = :identificadorSerieDocumental AND e.fechaApertura >= :primeraFecha AND e.fechaApertura <= :segundaFecha ORDER BY e.numeroExpediente DESC LIMIT 1")
    Optional<Short> numeroExpedienteMasProximo(String unidadAdministrativaGeneradora, Short identificadorSerieDocumental, LocalDate primeraFecha, LocalDate segundaFecha);

}
