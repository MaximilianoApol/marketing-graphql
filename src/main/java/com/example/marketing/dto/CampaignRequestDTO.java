package com.example.marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CampaignRequestDTO(

		// La BD tiene length 100
		@NotBlank(message = "El nombre es obligatorio") @Size(max = 100) String name,

		@NotNull(message = "El ID del usuario creador es obligatorio") Integer creatorUserId,

		// El estado es opcional en el request, ya que la BD tiene un valor por defecto (TRUE)
		Boolean isActive

) {
}