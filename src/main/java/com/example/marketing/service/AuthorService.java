package com.example.marketing.service;

import com.example.marketing.dto.AuthorRequestDTO;
import com.example.marketing.dto.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {



	AuthorResponseDTO create(AuthorRequestDTO request);

	AuthorResponseDTO update(Integer id, AuthorRequestDTO request);
	//  1. Buscar autor por ID
	AuthorResponseDTO findById(Integer authorId);

	//  2. Obtener autores ordenados por número de seguidores (paginado)
	List<AuthorResponseDTO> getAllOrderByFollowers(int page, int pageSize);

	//  3. Filtrar autores verificados (paginado)
	List<AuthorResponseDTO> findVerifiedAuthors(int page, int pageSize);

	//  4. Obtener influencers prioritarios (paginado)
	List<AuthorResponseDTO> findPriorityInfluencers(int page, int pageSize);

}
