package tn.esprit.spring.services.Interfaces;

import java.util.List;

import tn.esprit.spring.DAO.entities.Claim;

public interface ClaimService {
	
	List<Claim> retrieveAllClaims();

	Claim addClaim(Claim c);

	void deleteClaim(Long id);

	Claim updateClaim(Claim c);
	Claim retrieveClaim(Long id);

}
