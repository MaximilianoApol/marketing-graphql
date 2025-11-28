package com.example.marketing.dto;
import lombok.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponseDTO {
	private Integer campaignId;
	private String name;

	// Propiedades según el modelo corregido:
	private Boolean isActive;
	private OffsetDateTime creationDate;

	private UserResponseDTO creatorUser;
}