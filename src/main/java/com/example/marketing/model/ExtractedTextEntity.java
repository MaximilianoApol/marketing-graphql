package com.example.marketing.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "extracted_text_entities")
public class ExtractedTextEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private Integer entityId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "text_analysis_id", nullable = false)
	private TextAnalysis textAnalysis;

	@Column(name = "entity_text", nullable = false, length = 255)
	private String entityText;

	@Column(name = "entity_type", length = 50)
	private String entityType;

	@Column(name = "confidence_score", precision = 5, scale = 4)
	private BigDecimal confidenceScore;
}
