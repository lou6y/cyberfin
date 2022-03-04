package tn.esprit.spring.service.inter;

import java.util.List;

import tn.esprit.spring.DAO.entities.Offer;

public interface IOfferService {


		 List<Offer> retrieveAllOffers(); 
		 Offer addOffer(Offer O);
		 void deleteOffer(String id);
		 Offer updateOffer(Offer O);
		 Offer retrieveOffer(String id);
	
	
}
