package tn.esprit.spring.services.Implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.DAO.repositories.ClaimRepository;
import tn.esprit.spring.services.Interfaces.ClaimService;
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
