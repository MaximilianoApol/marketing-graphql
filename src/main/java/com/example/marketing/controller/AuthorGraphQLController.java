package com.example.marketing.controller;

import com.example.marketing.dto.AuthorRequestDTO;
import com.example.marketing.dto.AuthorResponseDTO;
import com.example.marketing.service.AuthorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthorGraphQLController {

	private final AuthorService authorService;


	@QueryMapping(name = "getAuthorById")
	public AuthorResponseDTO getAuthorById(@Argument Integer id) {
		log.info("GraphQL Query → getAuthorById(id={})", id);
		return authorService.findById(id);
	}

	@QueryMapping(name = "getAuthorsOrderByFollowers")
	public List<AuthorResponseDTO> getAuthorsOrderByFollowers(
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query → getAuthorsOrderByFollowers(page={}, size={})", page, size);
		return authorService.getAllOrderByFollowers(page, size);
	}

	@QueryMapping(name = "getVerifiedAuthors")
	public List<AuthorResponseDTO> getVerifiedAuthors(
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query → getVerifiedAuthors(page={}, size={})", page, size);
		return authorService.findVerifiedAuthors(page, size);
	}

	@QueryMapping(name = "getPriorityInfluencers")
	public List<AuthorResponseDTO> getPriorityInfluencers(
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query → getPriorityInfluencers(page={}, size={})", page, size);
		return authorService.findPriorityInfluencers(page, size);
	}

	@MutationMapping(name = "updateAuthor")
	public AuthorResponseDTO updateAuthor(
			@Argument Integer id,
			@Argument AuthorRequestDTO req
	) {
		log.info("GraphQL Mutation → updateAuthor(id={}, req={})", id, req);
		return authorService.update(id, req);
	}
}
