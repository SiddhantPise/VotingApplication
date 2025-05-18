package com.SP.voting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name = "voter_id",unique = true)
	@JsonIgnore
	private Voter voter;
	
	@ManyToOne
    @JoinColumn(name = "candidate_id")
	@JsonIgnore
    private Candidate candidate;
	
	@JsonProperty("voterId")
	public Long getVoterId() {
		return voter!=null ? voter.getId():null;
	}
	
	@JsonProperty("candidateId")
	public Long getCandidateId() {
		return candidate!=null ? candidate.getId():null;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	public Candidate getCandiate() {
		return candidate;
	}

	public void setCandiate(Candidate candiate) {
		this.candidate = candiate;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", voter=" + voter + ", candidate=" + candidate + "]";
	}
	
	


}
