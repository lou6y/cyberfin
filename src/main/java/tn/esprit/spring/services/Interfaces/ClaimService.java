package tn.esprit.spring.services.Interfaces;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.DAO.entities.Transaction;

public interface ClaimService {
	
	List<Claim> retrieveAllClaims();

	Claim addClaim(Claim c);

	void deleteClaim(Long id);

	Claim updateClaim(Claim c);
	Claim retrieveClaim(Long id);

	//insert
	void failedTransact(Long transaction_transaction_id, Long account_id,String transaction_type, double amount,String source,String status, String reason_code,LocalDateTime created_at,String state);

	Long findTopByOrderByTransactIdDesc(LocalDateTime created_at);
	
	int updateClByClaimId(String state,Long claim_id);
	
	
	
	public String verifyBadWords(String sentence);
	
	
	
	
}
