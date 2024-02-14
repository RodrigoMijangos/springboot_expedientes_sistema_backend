package com.sistema_expedientes.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationRequestDTO(
        @NotNull(message = "username cannot be null")
        @NotBlank(message = "username cannot be blank")
        String username,
        @NotNull(message = "email cannot be null")
        @NotBlank(message = "email cannot be blank")
        String email,
        @NotNull(message = "Password cannot be null")
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, max = 30, message = "Password must be between {min} and {max} characters")
        String password
) {
}
