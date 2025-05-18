package com.SP.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SP.voting.dto.ElectionResultRequestDTO;
import com.SP.voting.dto.ElectionResultResponseDTO;
import com.SP.voting.entity.Candidate;
import com.SP.voting.entity.ElectionResult;
import com.SP.voting.service.ElectionResultService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin
public class ElectionResultController {

	private ElectionResultService electionResultService;

	@Autowired
	public ElectionResultController(ElectionResultService electionResultService) {
		this.electionResultService = electionResultService;
	}
	
	@PostMapping("/declareResult")
	public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultDTO){
		ElectionResult result = electionResultService.declareElectionResult(electionResultDTO.getElectionName());
		ElectionResultResponseDTO responseDTO = new ElectionResultResponseDTO(result.getElection_name(),result.getTotalVotes(),result.getWinnerId(),result.getWinner().getVoteCount(),result.getWinner().getName());
//		responseDTO.setElectionName();
//		responseDTO.setTotalVotes();
//		responseDTO.setWinnerId();
//		responseDTO.setWinnerVotes();
		
		return ResponseEntity.ok(responseDTO);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<ElectionResult>> getAllResult(){
		List<ElectionResult>results = electionResultService.getAllResults();
		return ResponseEntity.ok(results);
	}
}
