package com.example.marketing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
	private Integer authorId;
	private String username;
	private Boolean verified;
	private Integer follower;
	private Boolean priority;
	private OffsetDateTime firstRegistrationDate;
}