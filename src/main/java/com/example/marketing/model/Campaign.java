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

	@Column(name = "campaign_name", length = 100, nullable = false)
	private String name;

	@Column(name = "creation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private OffsetDateTime creationDate;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_user_id", nullable = false)
	private User creatorUser;
}