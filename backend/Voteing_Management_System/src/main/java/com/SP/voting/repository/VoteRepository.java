package com.SP.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SP.voting.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote,Long> {

}
