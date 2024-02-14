package com.sistema_expedientes.services.auth;

import com.sistema_expedientes.auth.Role;
import com.sistema_expedientes.auth.User;
import com.sistema_expedientes.auth.repository.UserRepository;
import com.sistema_expedientes.services.exceptions.UsernameNotFoundException;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("in the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void saveUser(User user, Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        logger.info(String.format("Saving user with username %s and de role %s", user.getUsername(), role.getAuthority()));
        userRepository.save(new User(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                roles));
        logger.info("User with username {} saved successfully", user.getUsername());
    }
}
