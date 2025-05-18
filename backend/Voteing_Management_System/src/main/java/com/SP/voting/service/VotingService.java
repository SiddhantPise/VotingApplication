package com.SP.voting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SP.voting.entity.Candidate;
import com.SP.voting.entity.Vote;
import com.SP.voting.entity.Voter;
import com.SP.voting.exception.ResourseNotFoundExecption;
import com.SP.voting.exception.VoteNotAllowedException;
import com.SP.voting.repository.CandiateRepository;
import com.SP.voting.repository.VoteRepository;
import com.SP.voting.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VotingService {

	private VoterRepository voterRepository;
	private CandiateRepository candiateRepository;
	private VoteRepository voteRepository;
	public VotingService(VoterRepository voterRepository, CandiateRepository candiateRepository,
			VoteRepository voteRepository) {
		this.voterRepository = voterRepository;
		this.candiateRepository = candiateRepository;
		this.voteRepository = voteRepository;
	}
	
	@Transactional
	public Vote castVote(Long voterId,Long candidateId) {
		
		if (!voterRepository.existsById(voterId)) {
			throw new ResourseNotFoundExecption("Voter Not Found with ID : "+voterId);
		}
		if (!candiateRepository.existsById(candidateId)) {
			throw new ResourseNotFoundExecption("Candidate Not Found with ID : "+candidateId);
		}
		
		Voter voter = voterRepository.findById(voterId).get();
		
		if(voter.getHasVoted()) {
			throw new VoteNotAllowedException("Voter ID : "+voterId+" has alredy casted Vote."); 
		}
		
		Candidate candidate = candiateRepository.findById(candidateId).get();
		Vote vote = new Vote();
		vote.setVoter(voter);
		vote.setCandiate(candidate);
		voteRepository.save(vote);
		
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candiateRepository.save(candidate);
		
		voter.setHasVoted(true);
		voterRepository.save(voter);
		
		return vote;
		
	}
	
	public List<Vote> getAllVotes(){
		return voteRepository.findAll();
	}
	
}
