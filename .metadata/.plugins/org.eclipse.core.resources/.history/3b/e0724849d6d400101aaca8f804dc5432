package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String manifesto; // optional

    private boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "voter_id", referencedColumnName = "voterId")
    private Voter voter; // link to voter who registered as candidate

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    // Constructors
    public Candidate() {}

    public Candidate(String name, Voter voter, Election election) {
        this.name = name;
        this.voter = voter;
        this.election = election;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getManifesto() { return manifesto; }
    public void setManifesto(String manifesto) { this.manifesto = manifesto; }
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
    public Voter getVoter() { return voter; }
    public void setVoter(Voter voter) { this.voter = voter; }
    public Election getElection() { return election; }
    public void setElection(Election election) { this.election = election; }
}
