package com.sistema_expedientes.repositories;

import com.sistema_expedientes.entities.Legajo;
import com.sistema_expedientes.entities.compositesKeys.LegajoCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegajoRepositorio extends JpaRepository<Legajo, LegajoCompositeKey> {
}
