package tn.esprit.spring.services.Interf;

import java.util.List;



import tn.esprit.spring.entity.*;

public interface InvestServices {

	List<Invest> retrieveAllClients();
	Invest addClient(Invest c);
	void deleteInvest(Long id);
	

	double Simulator(double montant, int mode);
	Invest updateInvest(Invest c);
	List<Invest> retrieveAllInvests();
	
	double ajouterInterets(int getSolde);
	
	Invest retrieveInvest(Long userId);
	
	
}