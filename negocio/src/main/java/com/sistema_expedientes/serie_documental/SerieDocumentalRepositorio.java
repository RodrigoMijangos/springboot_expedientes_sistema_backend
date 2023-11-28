package com.sistema_expedientes.serie_documental;

import com.sistema_expedientes.serie_documental.SerieDocumental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieDocumentalRepositorio extends JpaRepository<SerieDocumental, Short> {

    List<SerieDocumental> findAllBySeriePadre(SerieDocumental seriePadre);

}
