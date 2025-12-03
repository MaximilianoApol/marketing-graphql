package com.example.marketing.service;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicationService {



    PublicationResponseDTO findById(Integer publicationId);

    List<PublicationResponseDTO> findAll();

    Page<PublicationResponseDTO> getAllPublications(Pageable pageable);

    PublicationResponseDTO update(Integer publicationId, PublicationRequestDTO request);


    List<PublicationResponseDTO> findPotentialViralContent();

}