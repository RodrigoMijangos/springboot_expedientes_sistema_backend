package com.sistema_expedientes.entities.dto.response;

import com.sistema_expedientes.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginResponseDTO(
        @NotNull(message = "Username cannot be null")
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotNull(message = "jwt cannot be null")
        @NotBlank(message = "jwt cannot be blank")
        String token,
        Role role
) {
}
