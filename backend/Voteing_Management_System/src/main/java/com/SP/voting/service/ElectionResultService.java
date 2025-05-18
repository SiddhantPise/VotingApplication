package com.SP.voting.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SP.voting.entity.Candidate;
import com.SP.voting.entity.ElectionResult;
import com.SP.voting.exception.ResourseNotFoundExecption;
import com.SP.voting.repository.CandiateRepository;
import com.SP.voting.repository.ElectionResultRepository;
import com.SP.voting.repository.VoteRepository;

@Service
public class ElectionResultService {

	private ElectionResultRepository electionResultRepository;
	private VoteRepository voteRepository;
	private CandiateRepository candidateRepository;
	@Autowired
	public ElectionResultService(ElectionResultRepository electionResultRepository, VoteRepository voteRepository,
			CandiateRepository candidateRepository) {
		this.electionResultRepository = electionResultRepository;
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
	}
	
	public ElectionResult declareElectionResult(String electionName) {
		Optional<ElectionResult> exsitingResult = this.electionResultRepository.findByElectionName(electionName);
		if(exsitingResult.isPresent()) {
			return exsitingResult.get();
		}
		
		if (voteRepository.count()==0) {
			throw new IllegalStateException("Can not declare result,As no Votes has been casted.");
		}
		 List<Candidate>allCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
		if (allCandidates.isEmpty()) {
			throw new ResourseNotFoundExecption("No Candidate Available.");
		}
		 Candidate winner = allCandidates.get(0);
		 
		 int totalVotes = 0;
		 for (Candidate candidate : allCandidates) {
			totalVotes+=candidate.getVoteCount();
		}
		 
		 ElectionResult result = new ElectionResult();
		 result.setElection_name(electionName);
		 result.setWinner(winner);
		 result.setTotalVotes(totalVotes);
		 
		 return electionResultRepository.save(result);
		
	}
	
	public List<ElectionResult> getAllResults(){
		return electionResultRepository.findAll();
	}
	
}
