package com.SP.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SP.voting.dto.VoteRequestDTO;
import com.SP.voting.dto.VoteResponseDTO;
import com.SP.voting.entity.Candidate;
import com.SP.voting.entity.Vote;
import com.SP.voting.entity.Voter;
import com.SP.voting.service.CandiateService;
import com.SP.voting.service.VoterService;
import com.SP.voting.service.VotingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {

	private VotingService votingservice;
	private VoterService voterservice;
	private CandiateService candidateservice;

	@Autowired
	public VotingController(VotingService votingservice,VoterService voterservice,CandiateService candidateservice) {
		this.votingservice = votingservice;
		this.voterservice = voterservice;
		this.candidateservice = candidateservice;
	}
	
	@PostMapping("/cast")
	public ResponseEntity<VoteResponseDTO> castvote(@RequestBody @Valid VoteRequestDTO voterequest)
	{
		Vote vote  = votingservice.castVote(voterequest.getVoterId(), voterequest.getCandidateId());
		
		Voter voter = voterservice.getVoterById(voterequest.getVoterId());
		String voterName = voter.getName(); 
		
		Candidate candidate = candidateservice.getCandidateById(voterequest.getCandidateId());
		String candidateName = candidate.getName();
		
		VoteResponseDTO voteresponse = new VoteResponseDTO("Vote Casted Successfully",true,voterequest.getVoterId(),voterequest.getCandidateId(), voterName, candidateName);
		
		return new ResponseEntity<>(voteresponse,HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Vote>> getAllVotes(){
		List<Vote> voteList = votingservice.getAllVotes();
		return new ResponseEntity<>(voteList,HttpStatus.OK);
	}
}
