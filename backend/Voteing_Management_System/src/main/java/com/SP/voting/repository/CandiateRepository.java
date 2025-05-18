package com.SP.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SP.voting.entity.Candidate;


public interface CandiateRepository extends JpaRepository<Candidate, Long>{

	//Now Spring Boot automatically builds these queries ->
	//findAllByOrderByVotesDesc() ➔ SELECT * FROM candidate ORDER BY votes DESC
	List<Candidate> findAllByOrderByVoteCountDesc();
}
