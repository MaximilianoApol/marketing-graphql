package com.example.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.marketing.model.TextAnalysis;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface TextAnalysisRepository extends JpaRepository<TextAnalysis, Integer> {

	// ✅ CORRECTO: Navega a la propiedad 'publicationApiId' del objeto 'publication'
	boolean existsByPublication_PublicationApiId(Integer publicationApiId);

	@Query("""
			    SELECT t
			    FROM TextAnalysis t
			    WHERE t.sentiment = :sentiment
			    ORDER BY t.sentimentConfidenceScore DESC
			""")
	List<TextAnalysis> findBySentimentOrderByConfidence(String sentiment);

	@Query("""
			    SELECT t
			    FROM TextAnalysis t
			    WHERE t.crisisScore >= :minScore
			    ORDER BY t.crisisScore DESC
			""")
	List<TextAnalysis> findHighRiskAnalyses(BigDecimal minScore);

	@Query("""
			    SELECT t
			    FROM TextAnalysis t
			    JOIN t.publication p
			    WHERE p.campaign.campaignId = :campaignId
			    ORDER BY t.analysisDate DESC
			""")
	List<TextAnalysis> findByCampaignId(Integer campaignId);


	@Query("""
			    SELECT t
			    FROM TextAnalysis t
			    WHERE t.detectedLanguage = :lang
			    ORDER BY t.analysisDate DESC
			""")
	List<TextAnalysis> findByLanguage(String lang);
}
