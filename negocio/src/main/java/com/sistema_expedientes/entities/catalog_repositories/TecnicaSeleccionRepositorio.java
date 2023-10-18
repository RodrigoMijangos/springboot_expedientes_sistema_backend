package com.sistema_expedientes.entities.catalog_repositories;

import com.sistema_expedientes.entities.catalog_entities.CatalagoTecnicaSeleccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicaSeleccionRepositorio extends JpaRepository<CatalagoTecnicaSeleccion, Byte> {
}
