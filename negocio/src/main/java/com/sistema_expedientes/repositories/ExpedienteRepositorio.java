package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExpedienteRepositorio extends JpaRepository<Expediente, ExpedienteCompositeKey> {

    Optional<Expediente> findFirstByUnidadAdministrativaGeneradoraAndIdentificadorSerieDocumentalAndFechaAperturaBetweenOrderByNumeroExpediente(String unidadAdministrativaGeneradora, Short identificadorSerieDocumental, LocalDate primeraFecha, LocalDate segundaFecha);

}
