package com.example.marketing.model;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_api_id")
	private Integer authorId;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "is_verified", nullable = false)
	private Boolean isVerified = false;

	@Column(name = "follower_count")
	private Integer followerCount = 0;

	@Column(name = "is_priority_influencer", nullable = false)
	private Boolean isPriorityInfluencer = false;

	@Column(name = "first_registration_date", nullable = false)
	private OffsetDateTime firstRegistrationDate = OffsetDateTime.now();

}

