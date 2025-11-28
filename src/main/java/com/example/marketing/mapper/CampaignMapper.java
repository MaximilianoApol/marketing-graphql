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
        
        // Mapeo manual simple del usuario para evitar ciclos o complejidad
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
                .description(entity.getDescription())
                .status(entity.getStatus())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .creatorUser(userDto) // Asignamos el usuario
                .build();
    }

    // Aceptamos el User como parámetro extra
    public static Campaign toEntity(CampaignRequestDTO request, User creator) {
        if (request == null) return null;

        return Campaign.builder()
                .name(request.name())
                .description(request.description())
                .status(request.status())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .creatorUser(creator) // Asignamos la entidad User
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
    }
    
    // ... el método copyToEntity se mantiene igual para campos simples ...
    public static void copyToEntity(CampaignRequestDTO request, Campaign existing) {
        if (request.name() != null) existing.setName(request.name());
        if (request.description() != null) existing.setDescription(request.description());
        if (request.status() != null) existing.setStatus(request.status());
        if (request.startDate() != null) existing.setStartDate(request.startDate());
        if (request.endDate() != null) existing.setEndDate(request.endDate());
        existing.setUpdatedAt(OffsetDateTime.now());
    }
}