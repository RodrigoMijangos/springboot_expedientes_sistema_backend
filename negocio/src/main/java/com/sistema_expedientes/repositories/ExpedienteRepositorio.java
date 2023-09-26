package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.Expediente;
import com.sistema_expedientes.entities.compositesKeys.ExpedienteCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpedienteRepositorio extends JpaRepository<Expediente, ExpedienteCompositeKey> { }
