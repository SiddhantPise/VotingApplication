package com.SP.voting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElectionResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Election Name Is Required")
	@Column(name = "election_name")
    private String electionName;
	
	private int totalVotes;
	
	@OneToOne
	@JoinColumn(name = "winner_id")
	@JsonIgnore
	private Candidate winner;
	
	@JsonProperty("winnerId")
	public Long getWinnerId(){
		return winner!=null?winner.getId():null;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getElection_name() {
		return electionName;
	}

	public void setElection_name(String election_name) {
		this.electionName = election_name;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Candidate getWinner() {
		return winner;
	}

	public void setWinner(Candidate winner) {
		this.winner = winner;
	}
	
	@Override
	public String toString() {
		return "ElectionResult [id=" + id + ", election_name=" + electionName + ", totalVotes=" + totalVotes
				+ ", winner=" + winner + "]";
	}
}
