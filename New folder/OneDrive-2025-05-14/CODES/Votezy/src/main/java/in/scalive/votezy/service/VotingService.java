package in.scalive.votezy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.entity.Voter;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.exception.VoteNotAllowedException;
import in.scalive.votezy.repository.CandidateRepository;
import in.scalive.votezy.repository.VoteRepository;
import in.scalive.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;

@Service
public class VotingService {
   
	private VoteRepository voteRepository;
    private CandidateRepository candidateRepository;
    private VoterRepository voterRepository;
	public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository,
			VoterRepository voterRepository) {
		
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
	}
	@Transactional
	public Vote castVote(Long voterId,Long candidateId) {
		if(!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter not found with ID:"+voterId);
		}
		if(!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate not found with ID:"+candidateId);
		}
		Voter voter=voterRepository.findById(voterId).get();
		if(voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter ID:"+voterId+" has already casted vote");
		}
		Candidate candidate=candidateRepository.findById(candidateId).get();
		Vote vote=new Vote();
		vote.setCandidate(candidate);
		vote.setVoter(voter);
		//voteRepository.save(vote);
		
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candidateRepository.save(candidate);
		voter.setVote(vote);
		voter.setHasVoted(true);
		voterRepository.save(voter);
		return vote;
	}
public List<Vote> getAllVotes(){
    	return voteRepository.findAll();
    }
    
}
