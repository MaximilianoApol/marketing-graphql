package com.example.marketing.repository;

import com.example.marketing.model.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // <-- CORREGIDO: Importación correcta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	// Método para buscar por el estado activo, ahora usando la clase Pageable correcta
	Page<Campaign> findByIsActive(boolean isActive, Pageable pageable);


	// CORREGIDO:
	// 1. Usa c.creationDate en lugar de c.createdAt.
	// 2. Acepta Pageable como parámetro.
	// 3. Devuelve Page<Campaign> para manejo nativo de la paginación por Spring Data.
	@Query("""
    SELECT c
    FROM Campaign c
    WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
    ORDER BY c.creationDate DESC
""")
	Page<Campaign> searchByName(String name, Pageable pageable);
}