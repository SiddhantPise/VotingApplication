package com.SP.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectionResultResponseDTO {

	private String election_name;
	private int totalVotes;
	private Long winnerId;
	private int winnerVotes;
	private String winnerName;
	
	
	public ElectionResultResponseDTO(String election_name, int totalVotes, Long winnerId, int winnerVotes,
			String winnerName) {
		this.election_name = election_name;
		this.totalVotes = totalVotes;
		this.winnerId = winnerId;
		this.winnerVotes = winnerVotes;
		this.winnerName = winnerName;
	}

	public String getElectionName() {
		return election_name;
	}

	public void setElectionName(String electionName) {
		this.election_name = electionName;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Long getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(Long winnerId) {
		this.winnerId = winnerId;
	}

	public int getWinnerVotes() {
		return winnerVotes;
	}

	public void setWinnerVotes(int winnerVotes) {
		this.winnerVotes = winnerVotes;
	}
	
	
	public String getWinnerName() {
		return winnerName;
	}

	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}

	@Override
	public String toString() {
		return "ElectionResultResponseDTO [election_name=" + election_name + ", totalVotes=" + totalVotes
				+ ", winnerId=" + winnerId + ", winnerVotes=" + winnerVotes + ", winnerName=" + winnerName + "]";
	}

	
	
	
	
	
}
