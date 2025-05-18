package com.SP.voting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SP.voting.entity.Candidate;
import com.SP.voting.entity.Vote;
import com.SP.voting.entity.Voter;
import com.SP.voting.exception.DuplicateResourseException;
import com.SP.voting.exception.ResourseNotFoundExecption;
import com.SP.voting.repository.CandiateRepository;
import com.SP.voting.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VoterService {

	private VoterRepository voterRepository;
	private CandiateRepository candiateRepository;
	@Autowired
	public VoterService(VoterRepository voterRepository, CandiateRepository candiateRepository) {
		super();
		this.voterRepository = voterRepository;
		this.candiateRepository = candiateRepository;
	}
	
	public Voter registerVoter(Voter voter) {
		if (voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourseException("Voter with Email id"+voter.getEmail()+" is already exists.");
		}
		
		return voterRepository.save(voter);
		
	}
	
	
	public List<Voter>getAllVoters(){
		return voterRepository.findAll();
	}
	
	public Voter getVoterById(Long id) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourseNotFoundExecption("Voter with id "+id+" not found");
		}
		return voter;
	}
	
	public Voter updateVoter(Long id,Voter updatedvoter) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourseNotFoundExecption("Voter with id "+id+" not found");
		}
		if (updatedvoter.getName() != null) {
			voter.setName(updatedvoter.getName());
		}
		if (updatedvoter.getName() != null) {
			voter.setEmail(updatedvoter.getEmail());
		}
		
		
	    return voterRepository.save(voter);
	}
	
	@Transactional
	public void deleteVoter(Long id) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if(voter == null) {
			throw new ResourseNotFoundExecption("Can't delete Voter with "+id+" as it voter does not exists.");
		}
		
		Vote vote = voter.getVote();
		if(vote != null)
		{
			Candidate candidate = vote.getCandiate();
			candidate.setVoteCount(candidate.getVoteCount()-1);
			candiateRepository.save(candidate);
		}
		voterRepository.delete(voter);
	}
}
