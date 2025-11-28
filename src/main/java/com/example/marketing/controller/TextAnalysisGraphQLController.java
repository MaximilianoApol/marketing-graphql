package com.example.marketing.controller;

import com.example.marketing.dto.TextAnalysisRequestDTO;
import com.example.marketing.dto.TextAnalysisResponseDTO;
import com.example.marketing.service.TextAnalysisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TextAnalysisGraphQLController {

	private final TextAnalysisService textService;

	@QueryMapping(name = "getTextAnalysisById")
	public TextAnalysisResponseDTO getTextAnalysisById(@Argument Integer id) {
		log.info("GraphQL Query → getTextAnalysisById(id={})", id);
		return textService.findById(id);
	}

	@QueryMapping(name = "findTextAnalysisBySentiment")
	public Page<TextAnalysisResponseDTO> findTextAnalysisBySentiment(
			@Argument String sentiment,
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query → findTextAnalysisBySentiment(sentiment={}, page={}, size={})",
				sentiment, page, size);
		return textService.findBySentiment(sentiment, PageRequest.of(page, size));
	}

	@QueryMapping(name = "findTextAnalysisByCampaign")
	public Page<TextAnalysisResponseDTO> findTextAnalysisByCampaign(
			@Argument Integer campaignId,
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query  findTextAnalysisByCampaign(campaignId={}, page={}, size={})",
				campaignId, page, size);
		return textService.findByCampaign(campaignId, PageRequest.of(page, size));
	}

	@QueryMapping(name = "find Text Analy sisBy Language")
	public Page<TextAnalysisResponseDTO> findTextAnalysisByLanguage(
			@Argument String language,
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query  findTextAnalysisByLanguage(language={}, page={}, size={})",
				language, page, size);
		return textService.findByLanguage(language, PageRequest.of(page, size));
	}

	@QueryMapping(name = "findHighRiskTextAnalysis")
	public Page<TextAnalysisResponseDTO> findHighRiskTextAnalysis(
			@Argument Float minScore,
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query  findHighRiskTextAnalysis(minScore={}, page={}, size={})",
				minScore, page, size);
		return textService.findHighRisk(BigDecimal.valueOf(minScore), PageRequest.of(page, size));
	}

	// ---------------- MUTATIONS ----------------

	@MutationMapping(name = "createTextAnalysis")
	public TextAnalysisResponseDTO createTextAnalysis(@Argument TextAnalysisRequestDTO req) {
		log.info("GraphQL Mutation  createTextAnalysis(req={})", req);
		return textService.create(req);
	}

	@MutationMapping(name = "updateTextAnalysis")
	public TextAnalysisResponseDTO updateTextAnalysis(
			@Argument Integer id,
			@Argument TextAnalysisRequestDTO req
	) {
		log.info("GraphQL Mutation  updateTextAnalysis(id={}, req={})", id, req);
		return textService.update(id, req);
	}
}
