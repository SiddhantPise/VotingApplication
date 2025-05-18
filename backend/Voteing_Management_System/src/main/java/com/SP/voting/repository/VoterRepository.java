package com.SP.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SP.voting.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {

	boolean existsByEmail(String email);
}
