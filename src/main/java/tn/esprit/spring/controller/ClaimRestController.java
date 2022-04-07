package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.DAO.entities.ClaimInfo;
import tn.esprit.spring.DAO.repositories.ClaimRepository;
import tn.esprit.spring.DAO.repositories.TransactionRepository;
import tn.esprit.spring.services.Interfaces.ClaimService;

	
	@RestController
	@RequestMapping("/claim")
	public class ClaimRestController {
		
		@Autowired 
		TransactionRepository transactionRepository;	

	@Autowired
	ClaimService claimService;
	
	@Autowired
	ClaimRepository claimRepository;

	// http://localhost:8083/SpringMVC/claim/retrieve-all-claims
	@GetMapping("/retrieve-all-claims")
	@ResponseBody
	public List<Claim> getClaims() {
	List<Claim> listClaims = claimService.retrieveAllClaims();
	return listClaims;
	}
	
	// http://localhost:8083/SpringMVC/claim/retrieve-claim/1
	@GetMapping("/retrieve-claim/{claim_id}")
	@ResponseBody
	public Claim retrieveClaim(@PathVariable("claim_id") Long claim_id) {
	return claimService.retrieveClaim(claim_id);
	}

	/*
	
	// http://localhost:8083/SpringMVC/claim/add-claim
	@PostMapping("/add-claim")
	@ResponseBody
	public Claim addClaim(@RequestBody Claim c)
	{ //badwords
	//	c.setStatus(claimService.verifyBadWords(c.getStatus()));
		
	Claim claim = claimService.addClaim(c);
	return claim;
	}
*/
	
	// http://localhost:8083/SpringMVC/claim/modify-claim
	@PutMapping("/modify-claim")
	@ResponseBody
	public Claim modifyClaim(@RequestBody Claim claim) {
	return claimService.updateClaim(claim);
	}
	
	
	// http://localhost:8083/SpringMVC/claim/remove-claim/{claim_id}
	@DeleteMapping("/remove-claim/{claim_id}")
	@ResponseBody
	public void removeClaim(@PathVariable("claim_id") Long claim_id) {
	claimService.deleteClaim(claim_id);
	}
	
	
	@PutMapping("/treatclaim")
	@ResponseBody
	public int treatClaim(@RequestParam("claim_id") Long claim_id) {
	
		return claimService.updateClByClaimId("treated", claim_id);

	}
	
	
	
	@GetMapping("/claiminfo")
    public List<ClaimInfo> getClaimInfoWithConstrutorExp(){
		return claimRepository.getClaimInfoWithConstrutorExp();
	}
	
	
	
	
    	
	
	
}
	
									


