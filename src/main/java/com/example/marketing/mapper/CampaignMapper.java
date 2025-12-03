package com.example.marketing.mapper;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.dto.UserResponseDTO;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.User;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {


    public CampaignResponseDTO toResponse(Campaign entity) {
        if (entity == null) return null;

        UserResponseDTO userDto = null;

        try {
            if (entity.getCreatorUser() != null) {
                User user = entity.getCreatorUser();
                userDto = new UserResponseDTO(
                        user.getUserId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getRole() != null ? user.getRole().getRoleName() : "UNKNOWN",
                        user.isActive(),
                        user.getCreationDate()
                );
            }
        } catch (Exception e) {
            userDto = null;
        }

        return CampaignResponseDTO.builder()
                .campaignId(entity.getCampaignId())
                .name(entity.getName())
                .isActive(entity.getIsActive())
                .creationDate(entity.getCreationDate())
                .creatorUser(userDto)
                .build();
    }


    public Campaign toEntity(CampaignRequestDTO request, User creator) {
        if (request == null) return null;

        return Campaign.builder()
                .name(request.name())
                .isActive(request.isActive())
                .creatorUser(creator)
                .build();
    }


    public void copyToEntity(CampaignRequestDTO request, Campaign existing) {
        if (request == null || existing == null) return;

        if (request.name() != null)
            existing.setName(request.name());

        if (request.isActive() != null) {
            existing.setIsActive(request.isActive());
        }
    }
}