package com.example.marketing.mapper;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import com.example.marketing.model.Publication;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.Author;

public class PublicationMapper {

    // Método modificado para recibir dependencias
    public static Publication toEntity(PublicationRequestDTO dto, Campaign campaign, Author author) {
        if (dto == null) return null;
        
        return Publication.builder()
                .textContent(dto.textContent())
                .publicationDate(dto.publicationDate())
                .likes(dto.likes())
                .comments(dto.comments())
                .shares(dto.shares())
                .geolocation(dto.geolocation())
                .publicationUrl(dto.publicationUrl())
                .classificationPriority(dto.classificationPriority())
                .campaign(campaign) // Asignamos Campaña
                .author(author)     // Asignamos Autor
                .build();
    }

    public static PublicationResponseDTO toResponseDTO(Publication entity) {
        if (entity == null) return null;
        
        // Aquí deberías usar CampaignMapper.toResponse(entity.getCampaign()) 
        // y AuthorMapper.toResponse(entity.getAuthor())
        // Para simplificar este ejemplo, asumo que tienes acceso a esos mappers o creas DTOs básicos
        
        return PublicationResponseDTO.builder()
                .publicationApiId(entity.getPublicationApiId())
                .textContent(entity.getTextContent())
                .publicationDate(entity.getPublicationDate())
                .likes(entity.getLikes())
                .comments(entity.getComments())
                .shares(entity.getShares())
                .publicationUrl(entity.getPublicationUrl())
                .classificationPriority(entity.getClassificationPriority())
                .collectionDate(entity.getCollectionDate())
                // .campaign( ... ) 
                // .author( ... )
                .build();
    }
    
    // copyToEntity se mantiene para actualizar campos simples
    public static void copyToEntity(PublicationRequestDTO dto, Publication entity) {
         if (dto.textContent() != null) entity.setTextContent(dto.textContent());
         // ... mapear resto de campos simples ...
    }
}