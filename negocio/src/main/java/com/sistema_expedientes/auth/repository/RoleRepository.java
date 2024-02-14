package com.sistema_expedientes.auth.repository;

import com.sistema_expedientes.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByAuthority(String authority);

    boolean existsByAuthority(String authority);
}
