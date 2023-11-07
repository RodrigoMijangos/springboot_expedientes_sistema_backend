package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LegajoRepositorio extends JpaRepository<Legajo, LegajoCompositeKey>{

    @Query(
            "SELECT e.id.numeroLegajo " +
                    "FROM Legajo AS e " +
                    "WHERE e.id.identificadorSerieDocumental = :identificadorSerieDocumental " +
                    "AND e.id.unidadAdministrativaGeneradora = :unidadAdministrativaGeneradora " +
                    "AND e.id.numeroExpediente = :numeroExpediente " +
                    "AND e.id.fechaApertura = :fechaAperturaExpediente " +
                    "ORDER BY e.id.numeroLegajo DESC LIMIT 1"
    )
    public Optional<Short> getNumeroLegajo(Short identificadorSerieDocumental, String unidadAdministrativaGeneradora, Short numeroExpediente, LocalDate fechaAperturaExpediente);

    @Query("SELECT L FROM Legajo as L " +
            "WHERE L.id.identificadorSerieDocumental = :identificadorSerieDocumental " +
            "AND L.id.unidadAdministrativaGeneradora = :unidadAdministrativaGeneradora " +
            "AND L.id.numeroExpediente = :numeroExpediente " +
            "AND L.id.fechaApertura = :fechaAperturaExpediente " +
            "ORDER BY L.id.numeroLegajo")
    public List<Legajo> getLegajosByExpediente(Short identificadorSerieDocumental, String unidadAdministrativaGeneradora, Short numeroExpediente, LocalDate fechaAperturaExpediente);

}
