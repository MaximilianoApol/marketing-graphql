package com.example.marketing.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TextAnalysisRequestDTO(

		@NotNull(message = "El publicationId es obligatorio")
		Integer publicationId,

		@NotBlank(message = "El sentimiento no puede estar vacío")
		@Size(max = 20, message = "El sentimiento no puede superar 20 caracteres")
		String sentiment,

		@NotNull(message = "El score de confianza del sentimiento es obligatorio")
		@DecimalMin(value = "0.0", inclusive = true, message = "sentimentConfidenceScore mínimo 0.0")
		@DecimalMax(value = "1.0", inclusive = true, message = "sentimentConfidenceScore máximo 1.0")
		BigDecimal sentimentConfidenceScore,

		@NotBlank(message = "El idioma detectado es obligatorio")
		@Size(max = 10, message = "detectedLanguage hasta 10 caracteres")
		String detectedLanguage,

		@NotNull(message = "El crisisScore es obligatorio")
		@DecimalMin(value = "0.0", inclusive = true, message = "crisisScore mínimo 0.0")
		@DecimalMax(value = "100.0", inclusive = true, message = "crisisScore máximo 100.0")
		BigDecimal crisisScore,

		@NotNull(message = "La fecha de análisis es obligatoria")
		OffsetDateTime analysisDate

) {}

