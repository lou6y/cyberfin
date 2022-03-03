package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.services.Interfaces.ClaimService;

	
	@RestController
	@RequestMapping("/claim")
	public class ClaimRestController {

	@Autowired
	ClaimService claimService;

	// http://localhost:8083/SpringMVC/claim/retrieve-all-claims
	@GetMapping("/retrieve-all-claims")
	@ResponseBody
	public List<Claim> getClaims() {
	List<Claim> listClaims = claimService.retrieveAllClaims();
	return listClaims;
	}
	
	// http://localhost:8083/SpringMVC/claim/retrieve-claim/8
	@GetMapping("/retrieve-claim/{claim-id}")
	@ResponseBody
	public Claim retrieveClaim(@PathVariable("claim-id") Long claimId) {
	return claimService.retrieveClaim(claimId);
	}

	// http://localhost:8083/SpringMVC/claim/add-claim
	@PostMapping("/add-claim")
	@ResponseBody
	public Claim addClaim(@RequestBody Claim c)
	{
	Claim claim = claimService.addClaim(c);
	return claim;
	}



	// http://localhost:8083/SpringMVC/claim/remove-claim/{claim-id}
	@DeleteMapping("/remove-claim/{claim-id}")
	@ResponseBody
	public void removeClaim(@PathVariable("claim-id") Long claimId) {
	claimService.deleteClaim(claimId);
	}

	// http://localhost:8083/SpringMVC/claim/modify-claim
	@PutMapping("/modify-claim")
	@ResponseBody
	public Claim modifyClaim(@RequestBody Claim claim) {
	return claimService.updateClaim(claim);
	}
	
	
	
									
}

