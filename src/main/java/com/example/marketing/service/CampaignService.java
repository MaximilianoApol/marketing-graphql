package com.example.marketing.service;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;

public interface CampaignService {

	CampaignResponseDTO create(CampaignRequestDTO request);

	CampaignResponseDTO update(Integer id, CampaignRequestDTO request);

	CampaignResponseDTO getById(Integer id);

	List<CampaignResponseDTO> getAll();

	Page<CampaignResponseDTO> getAllPaged(Pageable pageable);

	Page<CampaignResponseDTO> searchByName(String name, Pageable pageable);

	Page<CampaignResponseDTO> findActiveDuring(OffsetDateTime now, Pageable pageable);
}
