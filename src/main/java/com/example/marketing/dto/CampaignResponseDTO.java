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
	private String description;
	private String status;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
