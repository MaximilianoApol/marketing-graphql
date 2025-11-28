package com.example.marketing.mapper;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.dto.UserResponseDTO;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.User;
import java.time.OffsetDateTime;

public class CampaignMapper {

    public static CampaignResponseDTO toResponse(Campaign entity) {
        if (entity == null) return null;

        // Mapeo manual simple del usuario
        UserResponseDTO userDto = null;
        if (entity.getCreatorUser() != null) {
            userDto = new UserResponseDTO(
                    entity.getCreatorUser().getUserId(),
                    entity.getCreatorUser().getFullName(),
                    entity.getCreatorUser().getEmail(),
                    entity.getCreatorUser().getRole().getRoleName(),
                    entity.getCreatorUser().isActive(),
                    entity.getCreatorUser().getCreationDate()
            );
        }

        return CampaignResponseDTO.builder()
                .campaignId(entity.getCampaignId())
                .name(entity.getName())
                // Nuevas propiedades
                .isActive(entity.getIsActive())
                .creationDate(entity.getCreationDate())
                .creatorUser(userDto)
                .build();
    }

    public static Campaign toEntity(CampaignRequestDTO request, User creator) {
        if (request == null) return null;

        return Campaign.builder()
                .name(request.name())
                .isActive(request.isActive()) // Toma el valor del DTO (si es null, la BD usa DEFAULT TRUE)
                .creatorUser(creator)
                // creationDate se genera en la BD, no se mapea aquí.
                .build();
    }

    public static void copyToEntity(CampaignRequestDTO request, Campaign existing) {
        if (request.name() != null) existing.setName(request.name());

        // Permite actualizar el estado (isActive) solo si el valor fue enviado.
        if (request.isActive() != null) {
            existing.setIsActive(request.isActive());
        }
    }
}