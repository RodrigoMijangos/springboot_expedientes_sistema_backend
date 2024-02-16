package com.sistema_expedientes.serie_documental;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieDocumentalRepositorio extends JpaRepository<SerieDocumental, Short> {

    //List<SerieDocumental> findAllBySeriePadre(SerieDocumental seriePadre);

    List<SerieDocumental> findAllByVigenteIsTrue();

    Optional<SerieDocumental> findByIdentificadorAndVigenteIsTrue(Short identificador);

    @Query("UPDATE SerieDocumental sd SET sd.vigente = false WHERE sd.identificador = :identificador AND sd.vigente = true")
    void setSerieDocumentalNoVigente(Short identificador);

    @Query("UPDATE SerieDocumental sd SET sd.vigente = true WHERE sd.identificador = :identificador AND sd.vigente = false")
    void setSerieDocumentalVigente(Short identificador);

}
