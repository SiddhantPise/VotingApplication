package com.SP.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SP.voting.entity.ElectionResult;


public interface ElectionResultRepository extends JpaRepository<ElectionResult,Long> {

	Optional<ElectionResult> findByElectionName(String electionName);
}
