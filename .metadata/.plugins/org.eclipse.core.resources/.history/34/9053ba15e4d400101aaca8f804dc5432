package com.example.repository;

import com.example.entity.Candidate;
import com.example.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    boolean existsByVoterVoterId(String voterId);

    // Fetch all candidates for a specific election
    List<Candidate> findByElection(Election election);

    // Fetch only approved candidates for a specific election
    List<Candidate> findByElectionAndApprovedTrue(Election election);

    // Optional: fetch all approved candidates across all elections
    List<Candidate> findByApprovedTrue();
}
