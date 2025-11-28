package com.example.marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

public record CampaignRequestDTO(

		@NotBlank(message = "El nombre es obligatorio")
		@Size(max = 150)
		String name,

		@Size(max = 500)
		String description,

		@NotBlank(message = "El status es obligatorio")
		String status, // ACTIVE, PAUSED, FINISHED

		@NotNull(message = "La fecha de inicio es obligatoria")
		OffsetDateTime startDate,

		OffsetDateTime endDate

) {}
