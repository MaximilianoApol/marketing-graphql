package com.example.marketing.service;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.mapper.CampaignMapper;
import com.example.marketing.model.Campaign;
import com.example.marketing.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

	private final CampaignRepository repository;

	@Override
	public CampaignResponseDTO create(CampaignRequestDTO request) {
		Campaign entity = CampaignMapper.toEntity(request);
		repository.save(entity);
		return CampaignMapper.toResponse(entity);
	}

	@Override
	public CampaignResponseDTO update(Integer id, CampaignRequestDTO request) {
		Campaign existing = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Campaign not found"));

		CampaignMapper.copyToEntity(request, existing);
		repository.save(existing);

		return CampaignMapper.toResponse(existing);
	}

	@Override
	public CampaignResponseDTO getById(Integer id) {
		Campaign entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Campaign not found"));
		return CampaignMapper.toResponse(entity);
	}

	@Override
	public List<CampaignResponseDTO> getAll() {
		return repository.findAll()
				.stream()
				.map(CampaignMapper::toResponse)
				.toList();
	}


	@Override
	public Page<CampaignResponseDTO> getAllPaged(Pageable pageable) {
		return repository.findAll(pageable)
				.map(CampaignMapper::toResponse);
	}

	@Override
	public Page<CampaignResponseDTO> searchByName(String name, Pageable pageable) {
		return repository.searchByName(name)
				.stream()
				.map(CampaignMapper::toResponse)
				.collect(java.util.stream.Collectors.collectingAndThen(
						java.util.stream.Collectors.toList(),
						list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())
				));
	}

	@Override
	public Page<CampaignResponseDTO> findActiveDuring(OffsetDateTime now, Pageable pageable) {
		List<CampaignResponseDTO> list = repository.findActiveDuring(now)
				.stream()
				.map(CampaignMapper::toResponse)
				.toList();

		return new org.springframework.data.domain.PageImpl<>(list, pageable, list.size());
	}
}
