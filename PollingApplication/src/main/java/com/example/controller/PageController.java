package com.example.controller;

import com.example.entity.Election;
import com.example.service.CandidateService;
import com.example.service.ElectionService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final ElectionService electionService;
    private final CandidateService candidateService;
    private final UserService voterService;

    // =======================
    // Constructor injection (no Lombok)
    // =======================
    public PageController(ElectionService electionService,
                          CandidateService candidateService,
                          UserService voterService) {
        this.electionService = electionService;
        this.candidateService = candidateService;
        this.voterService = voterService;
    }

    // =======================
    // HOME PAGE
    // =======================
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // =======================
    // ADMIN DASHBOARD
    // =======================
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    // =======================
    // ADMIN LOGIN
    // =======================
    @GetMapping("/admin/login")
    public String adminLogin() {
        return "login-admin";
    }

    // =======================
    // VOTER LOGIN & SIGNUP
    // =======================
    @GetMapping("/voter/login")
    public String voterLogin() {
        return "login-voter";
    }

    @GetMapping("/voter/signup")
    public String voterSignup() {
        return "register-voter";
    }

    // =======================
    // REGISTER VOTER
    // =======================
    @GetMapping("/admin/register-voter")
    public String registerVoterPage() {
        return "register-voter";
    }

    // =======================
    // REGISTER CANDIDATE (Page)
    // =======================
    @GetMapping("/admin/register-candidate")
    public String registerCandidatePage(Model model) {
        model.addAttribute("elections", electionService.getAllElections());
        return "register-candidate";
    }

    // Voter self-register as candidate
    @GetMapping("/voter/register-candidate")
    public String voterRegisterCandidatePage(Model model) {
        model.addAttribute("elections", electionService.getAllElections());
        return "voter-register-candidate";
    }

    // =======================
    // CREATE ELECTION FORM
    // =======================
    @GetMapping("/admin/create-election")
    public String createElectionPage() {
        return "create-election";
    }

    // =======================
    // VOTER: VIEW ELECTIONS
    // =======================
    @GetMapping("/voter/elections")
    public String viewElections(Model model) {
        model.addAttribute("elections", electionService.getAllElections());
        return "elections";
    }

    // =======================
    // VOTER: VOTE PAGE (Load Candidates)
    // =======================
    @GetMapping("/voter/vote")
    public String votePage(@org.springframework.web.bind.annotation.RequestParam(value = "id", required = false) Long electionId,
                           Model model) {
        // Use provided election or fallback to active/latest
        Election election = (electionId != null)
                ? electionService.getElectionDetailsEntity(electionId)
                : electionService.getActiveElection(); // make sure this method exists
        model.addAttribute("election", election);

        // Load candidates for this election
        model.addAttribute("candidates", candidateService.getCandidatesByElection(election.getId()));

        return "vote";
    }

    // =======================
    // VOTE SUCCESS
    // =======================
    @GetMapping("/vote/success")
    public String voteSuccess() {
        return "vote-success";
    }

    // =======================
    // ANNOUNCEMENTS
    // =======================
    @GetMapping("/announcements")
    public String announcements(Model model) {
        return "announcements";
    }

    // =======================
    // POLLING PERCENTAGE VIEW (Admin)
    // =======================
    @GetMapping("/admin/polling-percentage")
    public String pollingPercentage(Model model) {
        model.addAttribute("elections", electionService.getAllElections());
        return "polling-percentage";
    }
}
