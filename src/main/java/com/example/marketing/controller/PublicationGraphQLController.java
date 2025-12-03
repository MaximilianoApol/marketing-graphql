package com.example.marketing.controller;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import com.example.marketing.service.PublicationService;

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
public class PublicationGraphQLController {

    private final PublicationService publicationService;

    @QueryMapping(name = "findPublicationById")
    public PublicationResponseDTO findPublicationById(@Argument Integer id) {
        log.info("GraphQL Query → findPublicationById(id={})", id);
        return publicationService.findById(id);
    }

    @QueryMapping(name = "findAllPublications")
    public List<PublicationResponseDTO> findAllPublications() {
        log.info("GraphQL Query → findAllPublications");
        return publicationService.findAll();
    }

    @QueryMapping(name = "getAllPublicationsPaged")
    public Page<PublicationResponseDTO> getAllPublicationsPaged(
            @Argument int page,
            @Argument int size
    ) {
        log.info("GraphQL Query → getAllPublicationsPaged(page={}, size={})", page, size);
        return publicationService.getAllPublications(PageRequest.of(page, size));
    }

    @QueryMapping(name = "findPotentialViralContent")
    public List<PublicationResponseDTO> findPotentialViralContent() {
        log.info("GraphQL Query → findPotentialViralContent");
        return publicationService.findPotentialViralContent();
    }

    @MutationMapping(name = "updatePublication")
    public PublicationResponseDTO updatePublication(
            @Argument Integer publicationId,
            @Argument PublicationRequestDTO req
    ) {
        log.info("GraphQL Mutation → updatePublication(id={}, req={})", publicationId, req);
        return publicationService.update(publicationId, req);
    }
}