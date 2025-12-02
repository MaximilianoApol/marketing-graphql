package com.example.marketing.service;

import com.example.marketing.dto.AuthorRequestDTO;
import com.example.marketing.dto.AuthorResponseDTO;
import com.example.marketing.mapper.AuthorMapper;
import com.example.marketing.model.Author;
import com.example.marketing.repository.AuthorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;
	private final AuthorMapper mapper;


	@SuppressWarnings("null")
	@Override
	public AuthorResponseDTO update(Integer id, AuthorRequestDTO request) {
		Author existing = authorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Author no encontrado"));

		mapper.copyToEntity(request, existing);
		authorRepository.save(existing);

		return mapper.toResponse(existing);
	}

	@Override
	public AuthorResponseDTO findById(Integer id) {
		@SuppressWarnings("null")
		Author entity = authorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Author no encontrado"));

		return mapper.toResponse(entity);
	}


	@Override
	@Transactional(readOnly = true)
	public List<AuthorResponseDTO> getAllOrderByFollowers(int page, int pageSize) {

		log.debug("Obteniendo autores ordenados por seguidores - Página: {}, Tamaño: {}", page, pageSize);

		return authorRepository
				.findAll(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "followerCount")))
				.getContent()
				.stream()
				.map(mapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthorResponseDTO> findVerifiedAuthors(int page, int pageSize) {

		log.debug("Filtrando autores verificados - Página: {}, Tamaño: {}", page, pageSize);

		return authorRepository
				.findVerifiedAuthors(PageRequest.of(page, pageSize))
				.getContent()
				.stream()
				.map(mapper::toResponse)
				.toList();
	}


	@Override
	@Transactional(readOnly = true)
	public List<AuthorResponseDTO> findPriorityInfluencers(int page, int pageSize) {

		log.debug("Obteniendo influencers prioritarios - Página: {}, Tamaño: {}", page, pageSize);

		List<Author> allPriority = authorRepository.findPriorityInfluencers();

		int fromIndex = Math.min(page * pageSize, allPriority.size());
		int toIndex = Math.min(fromIndex + pageSize, allPriority.size());

		return allPriority.subList(fromIndex, toIndex)
				.stream()
				.map(mapper::toResponse)
				.toList();
	}

}
