package com.example.marketing.repository;

import com.example.marketing.model.Publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {

    /**
     * Regla 5: Encuentra publicaciones con potencial de volverse virales.
     */
    @Query("SELECT p FROM Publication p " + 
    "WHERE p.likes > :minLikes AND p.shares > :minShares " +
    "AND p.publicationDate >= :timeLimit")
    List<Publication> findPotentialViralContentJPQL(
        @Param("minLikes") int minLikes,
        @Param("minShares") int minShares,
        @Param("timeLimit") OffsetDateTime timeLimit
        );

}