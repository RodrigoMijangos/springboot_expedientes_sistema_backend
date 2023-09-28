package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.SerieDocumental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieDocumentalRepositorio extends JpaRepository<SerieDocumental, Short> {

    List<SerieDocumental> findAllBySeriePadre(SerieDocumental seriePadre);

}
