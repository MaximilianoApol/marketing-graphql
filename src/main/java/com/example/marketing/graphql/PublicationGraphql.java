package com.example.marketing.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import com.example.marketing.service.PublicationService;

import jakarta.validation.Valid;

@Controller
public class PublicationGraphql {
    @Autowired
    private PublicationService service;

    @QueryMapping
    public List<PublicationResponseDTO> findAllPublications() {
        return service.findAll();
    }

    @MutationMapping
    public PublicationResponseDTO updatePublication(
            @Argument Integer publicationApiId,
            @Valid @Argument PublicationRequestDTO req) {
        return service.update(publicationApiId, req);
    }
}
