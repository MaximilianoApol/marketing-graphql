package com.example.marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationResponseDTO {

    @JsonProperty("publicationApiId")
    private Integer publicationApiId;

    @JsonProperty("textContent")
    private String textContent;

    @JsonProperty("publicationDate")
    private OffsetDateTime publicationDate;

    @JsonProperty("likes")
    private Integer likes;

    @JsonProperty("comments")
    private Integer comments;

    @JsonProperty("shares")
    private Integer shares;

    @JsonProperty("geolocation")
    private String geolocation;

    @JsonProperty("publicationUrl")
    private String publicationUrl;

    @JsonProperty("classificationPriority")
    private String classificationPriority;
    
    @JsonProperty("collectionDate")
    private OffsetDateTime collectionDate;

    @JsonProperty("campaign")
    private CampaignResponseDTO campaign;
    
    @JsonProperty("author")
    private AuthorResponseDTO author;
}