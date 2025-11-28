package com.example.marketing.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TextAnalysisResponseDTO(
		Integer textAnalysisId,
		Integer publicationId,
		String sentiment,
		BigDecimal sentimentConfidenceScore,
		String detectedLanguage,
		BigDecimal crisisScore,
		OffsetDateTime analysisDate
) {}
