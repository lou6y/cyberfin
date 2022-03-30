package tn.esprit.spring.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.repositories.TreasuryRepository;
import tn.esprit.spring.services.Interfaces.TreasuryService;

@Service
public class TreasuryServiceImpl implements TreasuryService{

	@Autowired
	TreasuryRepository TreasuryRep;
	
	@Override
	public void changeTreasury(double new_treasuryamount) {
		TreasuryRep.changeTreasuryAmount(new_treasuryamount);
		
	}

	@Override
	public double getTreasury(double treasuryamount) {
		return TreasuryRep.getTreasuryAmount(treasuryamount);
		
	}


}
