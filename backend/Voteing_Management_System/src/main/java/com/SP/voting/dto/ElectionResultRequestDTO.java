package com.SP.voting.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {
	
	@NotBlank(message = "Election Name Required.")
	private String election_name;

	public String getElectionName() {
		return election_name;
	}

	public void setElectionName(String election_name) {
		this.election_name = election_name;
	}

	@Override
	public String toString() {
		return "ElectionResultRequestDTO [election_name=" + election_name + "]";
	}
	
	
}
