package com.sistema_expedientes.services.auth;

import com.sistema_expedientes.auth.Role;
import com.sistema_expedientes.auth.User;
import com.sistema_expedientes.auth.dto.LoginResponseDTO;
import com.sistema_expedientes.auth.repository.RoleRepository;
import com.sistema_expedientes.auth.repository.UserRepository;
import com.sistema_expedientes.services.exceptions.UsernameAlreadyTakenException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    public void registerUser(String username, String email, String password) {
        logger.info("in Authentication Service - register");
        if (isUsersExists(username)) {
            throw new UsernameAlreadyTakenException("Username already taken");
        }
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByAuthority("ADMIN").orElseThrow(() -> new RuntimeException("ADMIN role not found"));
            logger.info("role - admin: {} ", adminRole);
            Set<Role> authorities = new HashSet<>();
            authorities.add(adminRole);
            String encodedPassword = passwordEncoder.encode(password);
            userRepository.save(new User(0L,username, email,encodedPassword,authorities));
            return;
        }
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new RuntimeException("USER role not found"));
        logger.info("role - user: {} ", userRole);

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        userRepository.save(new User(0L,username, email,encodedPassword,authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {
        logger.info("login user: {} -- {}", username, password );
        if (!isUsersExists(username)) {
            throw new UsernameNotFoundException("User not found");
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        String token = tokenService.generateJwt(auth);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = (Role) user.getAuthorities().stream().findFirst().orElseThrow(() -> new RuntimeException("User has no role"));
        return new LoginResponseDTO(username,token,role);
    }

    private boolean isUsersExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
