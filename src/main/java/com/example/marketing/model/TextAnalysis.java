package com.example.marketing.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "text_analysis")
public class TextAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_analysis_id")
    private Integer textAnalysisId;

    // --- Relación 1:1 con publications ---
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_api_id", nullable = false, unique = true)
    private Publication publication;

    @Column(name = "sentiment", length = 20)
    private String sentiment;

    @Column(name = "sentiment_confidence_score", precision = 5, scale = 4)
    private BigDecimal sentimentConfidenceScore;

    @Column(name = "detected_language", length = 10)
    private String detectedLanguage;

    @Column(name = "crisis_score", precision = 5, scale = 2)
    private BigDecimal crisisScore;

    @Builder.Default // <--- AGREGA ESTA ANOTACIÓN AQUÍ
    @Column(name = "analysis_date", nullable = false)
    private OffsetDateTime analysisDate = OffsetDateTime.now();

    // --- Relación 1:N con extracted_text_entities ---
    @OneToMany(mappedBy = "textAnalysis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtractedTextEntity> extractedTextEntities;
}