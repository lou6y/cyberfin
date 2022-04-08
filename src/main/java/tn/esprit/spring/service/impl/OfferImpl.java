package tn.esprit.spring.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
//import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import org.springframework.data.domain.Sort;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.DAO.entities.User;
import tn.esprit.spring.service.inter.IOfferService;

import tn.esprit.spring.DAO.repository.OfferRepository;
import tn.esprit.spring.DAO.repository.TransactionRepository;
import tn.esprit.spring.DAO.repository.UserRepository;
import tn.esprit.spring.DAO.repository.RatingRepository;
@Service
@Slf4j
public class OfferImpl implements IOfferService {
   
	@Autowired     
	OfferRepository OfferRepository ;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TransactionRepository TransactionRep;
	
	@Autowired
	RatingRepository RatingRepository;
	
	@Override
	public Offer modifyOffer(Offer O) {
		
		Offer OfferSaved = null;
		OfferSaved = OfferRepository.save(O);
		
		return OfferSaved;
	}
	
	
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
	public int deleteoldoffer() {
		// TODO Auto-generated method stub
		  OfferRepository.deleteoldoffer();
		return 1;
	}      
	
	  
	  
	/*  @Override
		public long participation(long UserId, long OfferId) {
			// TODO Auto-generated method stub
			InfoParticipation p = new InfoParticipation();
			Offer offer = OfferRepository.findById(OfferId).orElse(null); 
			User user = userRepository.findById(UserId).orElse(null);
			Date d = new Date();
			p.setOffer(offer);
			p.setUser(user);
			p.setDatepost(d);
			InfoParticipationRepository.save(p);
			return 1;
		    }*/
	
    
	@Override
	public Offer updateOffer(Offer O) {
		Offer OfferAdded = OfferRepository.save(O);
		return OfferAdded;
	}
	
	@Override
	public Offer retrieveOffer(String id) {
		log.info("in retrieveOffer id = " + id);
		Offer O = OfferRepository.findById(Long.parseLong(id)).get();
		log.info("Offer returned = : " + O);
		return O;
			}
	
	
	@Override
	public List<Offer> retrieveAllOffers() {
		
		
		List<Offer> Offers = (List<Offer>) OfferRepository.findAll(); 
		for(Offer ofr : Offers)
		{
			log.info("Offer +++ :" + ofr);
		}
					
		return Offers;
	}
	
	
	
	@Override
	public void affecterUserOffer(long UserId, long OfferId) {
		
		System.out.println("test0");

	Offer offer = OfferRepository.findById(OfferId).orElse(null);
	System.out.println("test00");

	User user = userRepository.findById(UserId).orElse(null);

	
	System.out.println("test1");

if(offer.getUsers()==null) {
	Set<User> users = new HashSet<User>();
	System.out.println("test2");
	users.add(user);
	System.out.println("test3");
	offer.setUsers(users);	
	System.out.println("test4");
	
		}
	else{
		offer.getUsers().add(user);
		
		
	
	}
OfferRepository.save(offer);

	}	







public String mostUsedOff(){
	List<Offer> offers = (List<Offer>) OfferRepository.findAll();
	Offer offerMax = null;
	int max = -1;
	for (Offer O : offers){
		if(O.getUsers().size() > max){
			max = O.getUsers().size();
			offerMax = O;
		}
	}
	return offerMax.getTypeOffer().name();
}


  	
  
@Override
public List<Offer> listAll() {
	
	
        return (List<Offer>) OfferRepository.findAll(Sort.by("ratingAvg").descending());
        
        
	
        
    }




//@Scheduled(cron = "*/10 * * * * *")
  public void downloadOfferExcel() throws InterruptedException {
   
	//load();
	List<Offer> offers = (List<Offer>) OfferRepository.findAll(Sort.by("ratingAvg").descending());

   // ByteArrayInputStream in = ExcelHelper.offerToExcel(offers);
    log.info("download offer xsl "+ 
      LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));  
  }




}



