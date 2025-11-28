package com.example.marketing.service;

import com.example.marketing.dto.TextAnalysisRequestDTO;
import com.example.marketing.dto.TextAnalysisResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface TextAnalysisService {

	TextAnalysisResponseDTO create(TextAnalysisRequestDTO request);

	TextAnalysisResponseDTO update(Integer id, TextAnalysisRequestDTO request);

	TextAnalysisResponseDTO findById(Integer id);

	Page<TextAnalysisResponseDTO> findBySentiment(String sentiment, Pageable pageable);

	Page<TextAnalysisResponseDTO> findByCampaign(Integer campaignId, Pageable pageable);

	Page<TextAnalysisResponseDTO> findByLanguage(String lang, Pageable pageable);

	Page<TextAnalysisResponseDTO> findHighRisk(BigDecimal minScore, Pageable pageable);
}
