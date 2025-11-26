package com.example.marketing.dto;

import java.time.OffsetDateTime;

public record UserResponseDTO(
    Integer userId,
    String fullName,
    String email,
    String roleName, // Enviamos solo el nombre del rol, no el objeto entero
    boolean isActive,
    OffsetDateTime creationDate
) {}
