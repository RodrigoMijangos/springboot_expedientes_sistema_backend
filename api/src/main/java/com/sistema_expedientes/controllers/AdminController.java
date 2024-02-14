package com.sistema_expedientes.controllers;

import com.sistema_expedientes.auth.Role;
import com.sistema_expedientes.auth.User;
import com.sistema_expedientes.services.auth.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin Controller")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Regresa el nivel de acceso conforme al rol admin")
    @GetMapping
    public String getLevel(){
        return "Admin level";
    }

    @Operation(summary = "Modificar el rol de los usuarios")
    @PutMapping("/{username}/role")
    public ResponseEntity<String> changueRole(@PathVariable String username, @RequestBody Role role){
        Optional<User> existUser = userService.findUserByUsername(username);

        if(existUser.isPresent()){
            User user = existUser.get();
            userService.saveUser(user, role);
            return ResponseEntity.ok("Role changued");
        }

        return ResponseEntity.badRequest().body("Failed to update role");
    }

}
