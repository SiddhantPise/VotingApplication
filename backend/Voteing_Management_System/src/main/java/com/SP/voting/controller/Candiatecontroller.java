package com.SP.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SP.voting.entity.Candidate;
import com.SP.voting.entity.Voter;
import com.SP.voting.service.CandiateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candiate")
@CrossOrigin
public class Candiatecontroller {

	private CandiateService candiateService;

	@Autowired
	public Candiatecontroller(CandiateService candiateService) {
		this.candiateService = candiateService;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Candidate> addcandiate(@RequestBody @Valid Candidate candiate){
		Candidate savedcandiCandidate = candiateService.addCandiate(candiate);
		return new ResponseEntity<>(savedcandiCandidate,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Candidate>getCandidateById(@PathVariable Long id){
		Candidate candiate = candiateService.getCandidateById(id);
		return new ResponseEntity<>(candiate,HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Candidate>>getAllCandidates(){
		
		List<Candidate>candidateList= candiateService.getAllCandiate();
		return new ResponseEntity<>(candidateList,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate>Candidate(@PathVariable Long id,@RequestBody Candidate candidate){
		Candidate updatedCandidate = candiateService.updateCandiate(id, candidate);
		return new ResponseEntity<>(updatedCandidate,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteCandidate(@PathVariable Long id){
		candiateService.deleteCandidate(id);
		return new ResponseEntity<>("Candidate with id: "+id+" deleted.",HttpStatus.OK);
		
	}
}
