package tn.esprit.spring.services.Implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.repositories.ClaimRepository;
import tn.esprit.spring.services.Interfaces.ClaimService;

import java.time.LocalDateTime;
import java.util.Arrays;
@Service
public class ClaimServiceImpl implements ClaimService{

	@Autowired
	ClaimRepository ClaimRep;
	
	@Override
	public List<Claim> retrieveAllClaims() {
		List<Claim> claims= (List<Claim>) (ClaimRep.findAll());
		//traja3ali de type iterable , donc nrodha traja3 type claim
		return claims;
	}

	@Override
	public Claim addClaim(Claim c) {
		return ClaimRep.save(c);
	}

	@Override
	public void deleteClaim(Long id) {
		ClaimRep.deleteById(id);
		
	}

	@Override
	public Claim updateClaim(Claim c) {
		return ClaimRep.save(c);
	}

	@Override
	public Claim retrieveClaim(Long id) {
		return ClaimRep.findById(id).get();
	}
	
	
	@Override
	public void failedTransact(Long transaction_transaction_id, Long account_id, String transaction_type,
			double amount, String source, String status, String reason_code, LocalDateTime created_at, String state) {
		ClaimRep.failedTransaction(transaction_transaction_id, account_id, transaction_type, amount, source,  status,reason_code, created_at, state);
	
	}

	@Override
	public Long findTopByOrderByTransactIdDesc(LocalDateTime created_at) {
		return ClaimRep.findTopByOrderByTransactionIdDesc(created_at);
	}
	
	
	@Override
	public int updateClByClaimId(String state, Long claim_id) {
		return ClaimRep.updateClaimByClaimId(state, claim_id);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
    public String verifyBadWords(String sentence)  {

        List<String> badWords =
                Arrays.asList("bb","cc");
        String[] words = sentence.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (badWords.contains(words[i]))
                words[i] = badWordsToStars(words[i]);
        }
        return String.join(" ", words);
    }

	public String badWordsToStars(String badwords) {

        String s = "";
        for (int i = 0; i < badwords.length(); i++) {
            s = s + "*";
        }
        return s;
    }

	

	
	

	
	

}
