package com.example.marketing.controller;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.service.CampaignService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CampaignGraphQLController {

	private final CampaignService campaignService;

	@QueryMapping(name = "getCampaignById")
	public CampaignResponseDTO getCampaignById(@Argument Integer id) {
		log.info("GraphQL Query → getCampaignById(id={})", id);
		return campaignService.getById(id);
	}

	@QueryMapping(name = "getAllCampaigns")
	public List<CampaignResponseDTO> getAllCampaigns() {
		log.info("GraphQL Query → getAllCampaigns");
		return campaignService.getAll();
	}

	@QueryMapping(name = "getCampaignsPaged")
	public Page<CampaignResponseDTO> getCampaignsPaged(
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query → getCampaignsPaged(page={}, size={})", page, size);
		return campaignService.getAllPaged(PageRequest.of(page, size));
	}

	@QueryMapping(name = "searchCampaignByName")
	public Page<CampaignResponseDTO> searchCampaignByName(
			@Argument String name,
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query → searchCampaignByName(name={}, page={}, size={})", name, page, size);
		return campaignService.searchByName(name, PageRequest.of(page, size));
	}

	@QueryMapping(name = "findActiveCampaigns")
	public Page<CampaignResponseDTO> findActiveCampaigns(
			@Argument boolean isActive,
			@Argument int page,
			@Argument int size
	) {
		log.info("GraphQL Query → findActiveCampaigns(isActive={}, page={}, size={})", isActive, page, size);
		return campaignService.findByIsActive(isActive, PageRequest.of(page, size));
	}

	@MutationMapping(name = "updateCampaign")
	public CampaignResponseDTO updateCampaign(
			@Argument Integer id,
			@Argument CampaignRequestDTO req
	) {
		log.info("GraphQL Mutation → updateCampaign(id={}, req={})", id, req);
		return campaignService.update(id, req);
	}
}