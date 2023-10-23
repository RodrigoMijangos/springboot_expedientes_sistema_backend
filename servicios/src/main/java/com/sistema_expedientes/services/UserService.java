package com.sistema_expedientes.services;

import com.sistema_expedientes.entities.User;
import com.sistema_expedientes.entities.Role;
import com.sistema_expedientes.repositories.UserRepository;
import com.sistema_expedientes.services.Exceptions.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    }
    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user, Role role){
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        userRepository.save(new User(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                roles
                ));
    }
}
