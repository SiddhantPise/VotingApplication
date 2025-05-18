package com.SP.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {
	
	private String message;
	private boolean success;
	private Long voterId;
	private Long candidateId;
	private String voterName;
	private String candidateName;
	
	
	


	


	public VoteResponseDTO(String message, boolean success, Long voterId, Long candidateId, String voterName,
			String candidateName) {
		
		this.message = message;
		this.success = success;
		this.voterId = voterId;
		this.candidateId = candidateId;
		this.voterName = voterName;
		this.candidateName = candidateName;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


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


	public String getVoterName() {
		return voterName;
	}


	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}


	public String getCandidateName() {
		return candidateName;
	}


	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}


	@Override
	public String toString() {
		return "VoteResponseDTO [message=" + message + ", success=" + success + ", voterId=" + voterId
				+ ", candidateId=" + candidateId + ", voterName=" + voterName + ", candidateName=" + candidateName
				+ "]";
	}


	
	
	
	
}
