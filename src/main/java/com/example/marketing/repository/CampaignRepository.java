package com.example.marketing.repository;

import com.example.marketing.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
	@Query("""
    SELECT c
    FROM Campaign c
    WHERE c.startDate <= :now
      AND c.endDate >= :now
    ORDER BY c.startDate ASC
""")
	List<Campaign> findActiveDuring(OffsetDateTime now);


	@Query("""
    SELECT c
    FROM Campaign c
    WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
    ORDER BY c.createdAt DESC
""")
	List<Campaign> searchByName(String name);

}
