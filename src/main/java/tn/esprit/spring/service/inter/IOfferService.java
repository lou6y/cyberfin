package tn.esprit.spring.service.inter;

import java.util.List;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.DAO.entities.Transaction;
public interface IOfferService {


		 List<Offer> retrieveAllOffers(); 
		 Offer addOffer(Offer O);
		 void deleteOffer(String id);
		 Offer updateOffer(Offer O);
		 Offer retrieveOffer(String id);
		 Offer modifyOffer(Offer O);
		 
		 void affecterUserOffer(long UserId,long OfferId);
		
		//String bestOffType();

		String mostUsedOff();
		public int deleteoldoffer();
		
		//public long participation(long UserId, long OfferId);//

		public List<Offer> listAll() ;
}
