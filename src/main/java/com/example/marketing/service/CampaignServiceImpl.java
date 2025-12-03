package com.example.marketing.service;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.mapper.CampaignMapper;
import com.example.marketing.model.Campaign;
import com.example.marketing.repository.CampaignRepository;
import com.example.marketing.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CampaignServiceImpl implements CampaignService {

	private final CampaignRepository repository;
	private final CampaignMapper campaignMapper;

	@Override
	public CampaignResponseDTO update(Integer id, CampaignRequestDTO request) {
		Campaign existing = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Campaign no encontrado"));

		campaignMapper.copyToEntity(request, existing);
		repository.save(existing);

		return campaignMapper.toResponse(existing);
	}

	@Override
	@Transactional(readOnly = true)
	public CampaignResponseDTO getById(Integer id) {
		Campaign entity = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Campaign no encontrado"));
		return campaignMapper.toResponse(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CampaignResponseDTO> getAll() {
		return repository.findAll()
				.stream()
				.map(campaignMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CampaignResponseDTO> getAllPaged(Pageable pageable) {
		return repository.findAll(pageable)
				.map(campaignMapper::toResponse);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CampaignResponseDTO> searchByName(String name, Pageable pageable) {
		return repository.searchByName(name, pageable)
				.map(campaignMapper::toResponse);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CampaignResponseDTO> findByIsActive(boolean isActive, Pageable pageable) {
		return repository.findByIsActive(isActive, pageable)
				.map(campaignMapper::toResponse);
	}
}