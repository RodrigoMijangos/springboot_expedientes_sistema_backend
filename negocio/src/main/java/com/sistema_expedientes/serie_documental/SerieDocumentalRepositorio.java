package com.sistema_expedientes.serie_documental;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import com.sistema_expedientes.unidad_administrativa.UnidadAdministrativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieDocumentalRepositorio extends JpaRepository<SerieDocumental, Short> {

    // List<SerieDocumental> findAllBySeriePadre(SerieDocumental seriePadre);

    // List<SerieDocumental> findAllByVigenteIsTrue();

    Optional<SerieDocumental> findByIdentificadorAndActiveIsTrue(Short identificador);

    /*@Query("UPDATE SerieDocumental sd SET sd.active = false WHERE sd.identificador = :identificador AND sd.active = true")
    void setSerieDocumentalNoVigente(Short identificador);

    @Query("UPDATE SerieDocumental sd SET sd.active = true WHERE sd.identificador = :identificador AND sd.active = false")
    void setSerieDocumentalVigente(Short identificador);*/

    List<SerieDocumental> findByActiveTrue(); // Consulta para recuperar entidades activas
    List<SerieDocumental> findByActiveFalse();

}
