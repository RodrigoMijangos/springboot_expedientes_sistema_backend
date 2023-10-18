package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.SerieDocumental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SerieDocumentalRepositorio extends JpaRepository<SerieDocumental, Short> {

    List<SerieDocumental> findAllBySeriePadre(SerieDocumental seriePadre);

}
