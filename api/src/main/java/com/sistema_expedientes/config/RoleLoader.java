package com.sistema_expedientes.config;

import com.sistema_expedientes.auth.Role;
import com.sistema_expedientes.auth.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleLoader implements CommandLineRunner {

    private final RoleRepository repository;

    public RoleLoader(RoleRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception{
        List<Role> roles = Arrays.asList(
                new Role("ADMIN"),
                new Role("USER")
        );

        for(Role role: roles){
            if (!repository.existsByAuthority(role.getAuthority())){
                repository.save(role);
            }
        }
    }

}
