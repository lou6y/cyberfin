package tn.esprit.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.service.inter.IOfferService;
import tn.esprit.spring.DAO.repository.OfferRepository;

@Service
public class OfferImpl implements IOfferService {
   
	@Autowired     
	OfferRepository OfferRepository ;
	

	
	@Override
	public Offer addOffer(Offer O) {
		
		Offer OfferSaved = null;
		OfferSaved = OfferRepository.save(O);
		
		return OfferSaved;
	}
	@Override
	public void deleteOffer(String id) {
		OfferRepository.deleteById(Long.parseLong(id));
		
	}
    
	@Override
	public Offer updateOffer(Offer O) {
		Offer OfferAdded = OfferRepository.save(O);
		return OfferAdded;
	}
	
	@Override
	public Offer retrieveOffer(String id) {
		
		Offer O = OfferRepository.findById(Long.parseLong(id)).get();
		
		return O;
			}
	
	
	@Override
	public List<Offer> retrieveAllOffers() {
		List<Offer> Offers = (List<Offer>) OfferRepository.findAll(); 
		
					
		return Offers;
	}
	
	

	
	
	
}



