package com.example.marketing.mapper;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.model.Campaign;

import java.time.OffsetDateTime;

public class CampaignMapper {

	public static CampaignResponseDTO toResponse(Campaign entity) {
		if (entity == null) return null;

		return CampaignResponseDTO.builder()
				.campaignId(entity.getCampaignId())
				.name(entity.getName())
				.description(entity.getDescription())
				.status(entity.getStatus())
				.startDate(entity.getStartDate())
				.endDate(entity.getEndDate())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}

	public static Campaign toEntity(CampaignRequestDTO request) {
		if (request == null) return null;

		return Campaign.builder()
				.name(request.name())
				.description(request.description())
				.status(request.status())
				.startDate(request.startDate())
				.endDate(request.endDate())
				.createdAt(OffsetDateTime.now())
				.updatedAt(OffsetDateTime.now())
				.build();
	}

	public static void copyToEntity(CampaignRequestDTO request, Campaign existing) {
		if (request == null || existing == null) return;

		if (request.name() != null)
			existing.setName(request.name());

		if (request.description() != null)
			existing.setDescription(request.description());

		if (request.status() != null)
			existing.setStatus(request.status());

		if (request.startDate() != null)
			existing.setStartDate(request.startDate());

		if (request.endDate() != null)
			existing.setEndDate(request.endDate());

		existing.setUpdatedAt(OffsetDateTime.now());
	}
}
