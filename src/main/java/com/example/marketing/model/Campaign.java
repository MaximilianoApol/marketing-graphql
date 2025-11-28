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

	// 1. CORRECCIÓN: 'name' debe mapearse a 'campaign_name'
	@Column(name = "campaign_name", length = 100, nullable = false)
	private String name;

	// 2. CORRECCIÓN: 'created_at' (el que causa el error) debe mapearse a 'creation_date'
	@Column(name = "creation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private OffsetDateTime creationDate;

	// 3. ADICIÓN: Mapear la columna 'is_active' que existe en la BD
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;


	// 5. Relación con User (Mantenida)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_user_id", nullable = false)
	private User creatorUser;
}