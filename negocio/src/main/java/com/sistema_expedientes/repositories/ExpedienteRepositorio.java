package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpedienteRepositorio extends JpaRepository<Expediente, ExpedienteCompositeKey> {

    Optional<Expediente> findFirstByUnidadAdministrativaAndPeriodoAperturaOrderByIdentificadorNumericoDesc(@NotNull Byte unidadAdministrativa, @NotNull Short periodoApertura);

}
