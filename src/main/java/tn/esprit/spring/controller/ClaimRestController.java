package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.services.Interfaces.ClaimService;

public class ClaimRestController {
	
	@RestController
	@RequestMapping("/claim")
	public class ClientRestController {

	@Autowired
	ClaimService claimService;

	// http://localhost:8083/SpringMVC/claim/retrieve-all-claims
	@GetMapping("/retrieve-all-claims")
	@ResponseBody
	public List<Claim> getClaims() {
	List<Claim> listClaims = claimService.retrieveAllClaims();
	return listClaims;
	}
									}
}

