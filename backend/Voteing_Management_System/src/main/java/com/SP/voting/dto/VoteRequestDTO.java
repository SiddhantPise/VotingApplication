package com.SP.voting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDTO {
	@NotNull(message = "Voter Id is Required")
	Long voterId;
	
	@NotNull(message = "Candidate Id is Required")
	Long candidateId;

	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	@Override
	public String toString() {
		return "VoteRequestDTO [voterId=" + voterId + ", candidateId=" + candidateId + "]";
	}
	
	
}
