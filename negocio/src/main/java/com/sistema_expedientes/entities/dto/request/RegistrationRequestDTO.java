package com.sistema_expedientes.entities.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationRequestDTO(
        @NotNull(message = "Usuario no puede ser nulo")
        @NotBlank(message = "Usuario no puede ser en blanco")
        String username,
        @NotNull(message = "email no puede ser nulo")
        @NotBlank(message = "email no puede ser en blanco")
        String email,
        @NotNull(message = "Contraseña no puede ser nulo")
        @NotBlank(message = "Contraseña no puede ser en blanco")
        @Size(min = 6, max = 30, message = "La contraseña debe ser entre {min} y {max} caracteres de largo")
        String password
) {
}
