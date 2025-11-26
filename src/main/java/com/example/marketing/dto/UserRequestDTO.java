package com.example.marketing.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "El nombre completo es obligatorio") 
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres") 
        String fullName,

        @NotBlank(message = "El email es obligatorio") 
        @Email(message = "El formato del email no es válido") 
        String email,

        @NotBlank(message = "La contraseña es obligatoria") 
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") 
        String password,

        @NotNull(message = "El ID del rol es obligatorio") 
        Integer roleId
) {
}
