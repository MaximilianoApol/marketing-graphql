package com.example.marketing.service;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import com.example.marketing.mapper.PublicationMapper;
import com.example.marketing.model.Publication;
import com.example.marketing.repository.AuthorRepository;
import com.example.marketing.repository.CampaignRepository;
import com.example.marketing.repository.PublicationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final CampaignRepository campaignRepository;
    private final AuthorRepository authorRepository;
    private final PublicationMapper publicationMapper;


    @Override
    @Transactional(readOnly = true)
    public PublicationResponseDTO findById(Integer publicationId) {
        Publication publication = publicationRepository.findByIdWithEagerDetails(publicationId)
                .orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada con ID: " + publicationId));

        return publicationMapper.toResponseDTO(publication);
    }


    @Override
    @Transactional(readOnly = true)
    public List<PublicationResponseDTO> findAll() {
        List<Publication> publications = publicationRepository.findAllWithEagerDetails();

        return publications.stream()
                .map(publicationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public Page<PublicationResponseDTO> getAllPublications(Pageable pageable) {
        Page<Publication> publicationPage = publicationRepository.findAll(pageable);
        return publicationPage.map(publicationMapper::toResponseDTO);
    }


    @Override
    @Transactional
    public PublicationResponseDTO update(Integer publicationId, PublicationRequestDTO request) {
        Publication existingPublication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada con ID: " + publicationId));

        publicationMapper.copyToEntity(request, existingPublication);
        Publication savedPublication = publicationRepository.save(existingPublication);

        return publicationMapper.toResponseDTO(savedPublication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublicationResponseDTO> findPotentialViralContent() {
        OffsetDateTime lastHour = OffsetDateTime.now().minus(1, ChronoUnit.HOURS);
        List<Publication> publications = publicationRepository
                .findPotentialViralContentJPQL(1000, 100, lastHour);

        return publications.stream()
                .map(publicationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}