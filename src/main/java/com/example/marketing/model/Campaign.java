package com.example.marketing.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaigns")
public class Campaign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "campaign_id")
	private Integer campaignId;

	@Column(name = "name", length = 150, nullable = false)
	private String name;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "status", length = 30, nullable = false)
	private String status;
	// Ejemplos: ACTIVE, PAUSED, FINISHED

	@Column(name = "start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime startDate;

	@Column(name = "end_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime endDate;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private OffsetDateTime createdAt;

	@Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private OffsetDateTime updatedAt;
}
