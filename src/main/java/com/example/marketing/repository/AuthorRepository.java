package com.example.marketing.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.marketing.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query("SELECT a FROM Author a WHERE a.isVerified = true")
	Page<Author> findVerifiedAuthors(Pageable pageable);

	@Query("SELECT a FROM Author a WHERE a.isPriorityInfluencer = true ORDER BY a.followerCount DESC")
	List<Author> findPriorityInfluencers();


}

