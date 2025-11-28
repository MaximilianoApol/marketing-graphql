package com.example.marketing.mapper;

import com.example.marketing.dto.*;
import com.example.marketing.model.TextAnalysis;
import com.example.marketing.model.Publication;

import java.time.OffsetDateTime;

public class TextAnalysisMapper {

	public static TextAnalysis toEntity(TextAnalysisRequestDTO request, Publication publication) {
		if (request == null || publication == null) return null;

		return TextAnalysis.builder()
				.publication(publication)
				.sentiment(request.sentiment())
				.sentimentConfidenceScore(request.sentimentConfidenceScore())
				.detectedLanguage(request.detectedLanguage())
				.crisisScore(request.crisisScore())
				.analysisDate(OffsetDateTime.now())
				.build();
	}

	public static TextAnalysisResponseDTO toResponse(TextAnalysis entity) {
		if (entity == null) return null;

		return TextAnalysisResponseDTO.builder()
				.textAnalysisId(entity.getTextAnalysisId())
				.publicationId(entity.getPublication().getPublicationApiId())
				.sentiment(entity.getSentiment())
				.sentimentConfidenceScore(entity.getSentimentConfidenceScore())
				.detectedLanguage(entity.getDetectedLanguage())
				.crisisScore(entity.getCrisisScore())
				.analysisDate(entity.getAnalysisDate())
				.build();
	}

	public static void copyToEntity(TextAnalysisRequestDTO request, TextAnalysis existing) {
		if (request == null || existing == null) return;

		existing.setSentiment(request.sentiment());
		existing.setSentimentConfidenceScore(request.sentimentConfidenceScore());
		existing.setDetectedLanguage(request.detectedLanguage());
		existing.setCrisisScore(request.crisisScore());
	}
}
