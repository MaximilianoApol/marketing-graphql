package com.example.marketing.service;

import com.example.marketing.dto.TextAnalysisRequestDTO;
import com.example.marketing.dto.TextAnalysisResponseDTO;
import com.example.marketing.mapper.TextAnalysisMapper;
import com.example.marketing.model.TextAnalysis;
import com.example.marketing.model.Publication;
import com.example.marketing.repository.PublicationRepository;
import com.example.marketing.repository.TextAnalysisRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TextAnalysisServiceImpl implements TextAnalysisService {

    private final TextAnalysisRepository repo;
    private final PublicationRepository publicationRepo;

    @SuppressWarnings("null")
    @Override
    public TextAnalysisResponseDTO create(TextAnalysisRequestDTO request) {

        @SuppressWarnings("null")
        Publication publication = publicationRepo.findById(request.publicationId())
                .orElseThrow(() -> new EntityNotFoundException("Publication no encontrada"));

        // CORRECCIÓN AQUÍ: Llamar al método renombrado
        if (repo.existsByPublication_PublicationApiId(request.publicationId())) {
            throw new IllegalStateException("Ya existe un análisis para esta publicación");
        }

        TextAnalysis entity = TextAnalysisMapper.toEntity(request, publication);
        repo.save(entity);

        return TextAnalysisMapper.toResponse(entity);
    }

    @SuppressWarnings("null")
    @Override
    public TextAnalysisResponseDTO update(Integer id, TextAnalysisRequestDTO request) {

        @SuppressWarnings("null")
        TextAnalysis existing = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TextAnalysis no encontrado"));

        TextAnalysisMapper.copyToEntity(request, existing);
        repo.save(existing);

        return TextAnalysisMapper.toResponse(existing);
    }

    @Override
    public TextAnalysisResponseDTO findById(Integer id) {
        @SuppressWarnings("null")
        TextAnalysis entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TextAnalysis no encontrado"));

        return TextAnalysisMapper.toResponse(entity);
    }


    @SuppressWarnings("null")
    @Override
    public Page<TextAnalysisResponseDTO> findBySentiment(String sentiment, Pageable pageable) {
        List<TextAnalysisResponseDTO> list = repo.findBySentimentOrderByConfidence(sentiment)
                .stream().map(TextAnalysisMapper::toResponse).toList();

        return new PageImpl<>(list, pageable, list.size());
    }

    @SuppressWarnings("null")
    @Override
    public Page<TextAnalysisResponseDTO> findByCampaign(Integer campaignId, Pageable pageable) {
        List<TextAnalysisResponseDTO> list = repo.findByCampaignId(campaignId)
                .stream().map(TextAnalysisMapper::toResponse).toList();

        return new PageImpl<>(list, pageable, list.size());
    }

    @SuppressWarnings("null")
    @Override
    public Page<TextAnalysisResponseDTO> findByLanguage(String lang, Pageable pageable) {
        List<TextAnalysisResponseDTO> list = repo.findByLanguage(lang)
                .stream().map(TextAnalysisMapper::toResponse).toList();

        return new PageImpl<>(list, pageable, list.size());
    }

    @SuppressWarnings("null")
    @Override
    public Page<TextAnalysisResponseDTO> findHighRisk(BigDecimal minScore, Pageable pageable) {
        List<TextAnalysisResponseDTO> list = repo.findHighRiskAnalyses(minScore)
                .stream().map(TextAnalysisMapper::toResponse).toList();

        return new PageImpl<>(list, pageable, list.size());
    }
}