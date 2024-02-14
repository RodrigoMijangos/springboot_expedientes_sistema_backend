package com.sistema_expedientes.controllers;

import com.sistema_expedientes.auth.dto.LoginRequestDTO;
import com.sistema_expedientes.auth.dto.LoginResponseDTO;
import com.sistema_expedientes.auth.dto.RegistrationRequestDTO;
import com.sistema_expedientes.services.auth.AuthenticationService;
import com.sistema_expedientes.services.exceptions.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        try{
            authenticationService.registerUser(registrationRequestDTO.username(), registrationRequestDTO.email(), registrationRequestDTO.password());
            return ResponseEntity.ok("User registered succesfully");
        }catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Validated @RequestBody LoginRequestDTO loginRequestDTO){
        try {
            LoginResponseDTO user = authenticationService.loginUser(loginRequestDTO.username(), loginRequestDTO.password());
            logger.trace("User info: {}", user);
            return ResponseEntity.ok(user);
        }catch (UsernameNotFoundException e){
            logger.info("exception: {}",e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
