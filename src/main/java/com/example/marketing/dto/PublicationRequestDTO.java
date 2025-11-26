package com.example.marketing.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record PublicationRequestDTO(

        String textContent,

        OffsetDateTime publicationDate,

        @Min(0) Integer likes,

        @Min(0) Integer comments,

        @Min(0) Integer shares,

        @Size(max = 100) String geolocation,

        @Size(max = 512) String publicationUrl,

        @Size(max = 50) String classificationPriority) {
}