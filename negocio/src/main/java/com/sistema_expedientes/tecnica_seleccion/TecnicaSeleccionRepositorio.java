package com.sistema_expedientes.tecnica_seleccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicaSeleccionRepositorio extends JpaRepository<CatalagoTecnicaSeleccion, Byte> {
}
