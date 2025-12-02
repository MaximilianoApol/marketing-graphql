package com.example.marketing.repository;

import com.example.marketing.model.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // <-- CORREGIDO: Importación correcta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	Page<Campaign> findByIsActive(boolean isActive, Pageable pageable);


	@Query("""
    SELECT c
    FROM Campaign c
    WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
    ORDER BY c.creationDate DESC
""")
	Page<Campaign> searchByName(String name, Pageable pageable);
}