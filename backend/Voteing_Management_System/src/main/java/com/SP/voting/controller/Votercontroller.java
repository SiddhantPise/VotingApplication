package com.SP.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.SP.voting.entity.Voter;
import com.SP.voting.service.VoterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin

public class Votercontroller {

	private VoterService voterservice;

	@Autowired
	public Votercontroller(VoterService voterservice) {
		this.voterservice = voterservice;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Voter> registervoter(@RequestBody @Valid Voter voter){
		Voter savedvoter = voterservice.registerVoter(voter);
		return new ResponseEntity<>(savedvoter,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Voter>getVoterById(@PathVariable Long id){
		Voter voter = voterservice.getVoterById(id);
		return new ResponseEntity<>(voter,HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Voter>>getAllVoters(){
		
		List<Voter>voterList= voterservice.getAllVoters();
		return new ResponseEntity<>(voterList,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Voter>updateVoter(@PathVariable Long id,@RequestBody Voter voter){
		Voter updatedVoter = voterservice.updateVoter(id, voter);
		return new ResponseEntity<>(updatedVoter,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteVoter(@PathVariable Long id){
		voterservice.deleteVoter(id);
		return new ResponseEntity<>("Voter with id: "+id+" deleted.",HttpStatus.OK);
		
	}
	
}
