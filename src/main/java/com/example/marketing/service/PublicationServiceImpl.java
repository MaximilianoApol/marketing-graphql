package com.example.marketing.service;

import com.example.marketing.dto.PublicationRequestDTO;
import com.example.marketing.dto.PublicationResponseDTO;
import com.example.marketing.mapper.PublicationMapper;
import com.example.marketing.model.Author;
import com.example.marketing.model.Campaign;
//import com.example.marketing.model.Author;
//import com.example.marketing.model.Campaign;
import com.example.marketing.model.Publication;
import com.example.marketing.repository.AuthorRepository;
import com.example.marketing.repository.CampaignRepository;
//import com.example.marketing.repository.AuthorRepository;
//import com.example.marketing.repository.CampaignRepository;
import com.example.marketing.repository.PublicationRepository; // ÚNICO repositorio para publicaciones

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


    @Override
    public PublicationResponseDTO create(PublicationRequestDTO request) {
        // 1. Buscar Campaña
        Campaign campaign = campaignRepository.findById(request.campaignId())
             .orElseThrow(() -> new EntityNotFoundException("Campaña no encontrada"));

        // 2. Buscar Autor
        Author author = authorRepository.findById(request.authorId())
             .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));

        // 3. Crear entidad con relaciones
        Publication newPublication = PublicationMapper.toEntity(request, campaign, author);
        newPublication.setCollectionDate(OffsetDateTime.now());
        
        publicationRepository.save(newPublication);
        return PublicationMapper.toResponseDTO(newPublication);
    }

    @Override
    @Transactional(readOnly = true)
    public PublicationResponseDTO findById(Integer publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada con ID: " + publicationId));
        return PublicationMapper.toResponseDTO(publication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublicationResponseDTO> findAll() {
        return publicationRepository.findAll().stream()
                .map(PublicationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PublicationResponseDTO> getAllPublications(Pageable pageable) {
        Page<Publication> publicationPage = publicationRepository.findAll(pageable);
        return publicationPage.map(PublicationMapper::toResponseDTO);
    }

    @Override
    public PublicationResponseDTO update(Integer publicationId, PublicationRequestDTO request) {
        Publication existingPublication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada con ID: " + publicationId));
        PublicationMapper.copyToEntity(request, existingPublication);
        Publication savedPublication = publicationRepository.save(existingPublication);
        return PublicationMapper.toResponseDTO(savedPublication);
    }

    @Override
    public void delete(Integer publicationId) {
        if (!publicationRepository.existsById(publicationId)) {
            throw new EntityNotFoundException(
                    "No se puede eliminar. Publicación no encontrada con ID: " + publicationId);
        }
        publicationRepository.deleteById(publicationId);
    }

    @Override
    public List<PublicationResponseDTO> findPotentialViralContent() {
        OffsetDateTime lastHour = OffsetDateTime.now().minus(1, ChronoUnit.HOURS);
        List<Publication> publications = publicationRepository.findPotentialViralContentJPQL(1000, 100, lastHour);
        return publications.stream()
                .map(PublicationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}