package com.example.marketing.mapper;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import com.example.marketing.model.Publication;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublicationMapper {

    private final CampaignMapper campaignMapper;
    private final AuthorMapper authorMapper;


    public PublicationResponseDTO toResponseDTO(Publication entity) {
        if (entity == null) return null;

        return PublicationResponseDTO.builder()
                .publicationApiId(entity.getPublicationApiId())
                .textContent(entity.getTextContent())
                .publicationDate(entity.getPublicationDate())
                .likes(entity.getLikes())
                .comments(entity.getComments())
                .shares(entity.getShares())
                .geolocation(entity.getGeolocation())
                .publicationUrl(entity.getPublicationUrl())
                .classificationPriority(entity.getClassificationPriority())
                .collectionDate(entity.getCollectionDate())
                .campaign(entity.getCampaign() != null ?
                        campaignMapper.toResponse(entity.getCampaign()) : null)
                .author(entity.getAuthor() != null ?
                        authorMapper.toResponse(entity.getAuthor()) : null)
                .build();
    }


    public Publication toEntity(PublicationRequestDTO dto, Campaign campaign, Author author) {
        if (dto == null) return null;

        return Publication.builder()
                .textContent(dto.textContent())
                .publicationDate(dto.publicationDate())
                .likes(dto.likes() != null ? dto.likes() : 0)
                .comments(dto.comments() != null ? dto.comments() : 0)
                .shares(dto.shares() != null ? dto.shares() : 0)
                .geolocation(dto.geolocation())
                .publicationUrl(dto.publicationUrl())
                .classificationPriority(dto.classificationPriority())
                .campaign(campaign)
                .author(author)
                .build();
    }


    public void copyToEntity(PublicationRequestDTO dto, Publication entity) {
        if (dto == null || entity == null) return;

        if (dto.textContent() != null)
            entity.setTextContent(dto.textContent());

        if (dto.publicationDate() != null)
            entity.setPublicationDate(dto.publicationDate());

        if (dto.likes() != null)
            entity.setLikes(dto.likes());

        if (dto.comments() != null)
            entity.setComments(dto.comments());

        if (dto.shares() != null)
            entity.setShares(dto.shares());

        if (dto.geolocation() != null)
            entity.setGeolocation(dto.geolocation());

        if (dto.publicationUrl() != null)
            entity.setPublicationUrl(dto.publicationUrl());

        if (dto.classificationPriority() != null)
            entity.setClassificationPriority(dto.classificationPriority());
    }
}