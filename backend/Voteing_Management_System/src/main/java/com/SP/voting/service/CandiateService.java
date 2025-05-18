package com.SP.voting.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SP.voting.entity.Candidate;
import com.SP.voting.entity.Vote;
import com.SP.voting.entity.Voter;
import com.SP.voting.exception.DuplicateResourseException;
import com.SP.voting.exception.ResourseNotFoundExecption;
import com.SP.voting.repository.CandiateRepository;

@Service
public class CandiateService {

	private CandiateRepository candiateRepository;

	@Autowired
	public CandiateService(CandiateRepository candiateRepository) {
		this.candiateRepository = candiateRepository;
	}
	
	public Candidate addCandiate(Candidate candiate) {
		return candiateRepository.save(candiate);
		
	}
	
	public List<Candidate>getAllCandiate(){
		return candiateRepository.findAll();
	}
	
	public Candidate getCandidateById(Long id) {
		Candidate candiate = candiateRepository.findById(id).orElse(null);
		if (candiate == null) {
			throw new ResourseNotFoundExecption("Candiate with id "+id+" not found");
		}
		return candiate;
		
	}
	
	public Candidate updateCandiate(Long id, Candidate UpdatedCandiate) {
		Candidate candiate = getCandidateById(id);
		if (UpdatedCandiate.getName()!=null) {
			candiate.setName(UpdatedCandiate.getName());
		}
		
		if (UpdatedCandiate.getEmail()!=null) {
			candiate.setEmail(UpdatedCandiate.getEmail());
		}
		
		if (UpdatedCandiate.getParty()!=null) {
			candiate.setParty(UpdatedCandiate.getParty());
		}
		return candiateRepository.save(candiate);
	}
	
	
	public void deleteCandidate(Long id) {
		Candidate candiate = getCandidateById(id);
		List<Vote>votes = candiate.getVote();
		for (Vote v : votes) {
			v.setCandiate(null);
		}
		candiate.getVote().clear();
		candiateRepository.delete(candiate);
	}
	
	
}
