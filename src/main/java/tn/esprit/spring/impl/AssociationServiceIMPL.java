package tn.esprit.spring.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entite.Account;
import tn.esprit.spring.entite.Association;
import tn.esprit.spring.entite.User;
import tn.esprit.spring.repository.AccountRepository;
import tn.esprit.spring.repository.AssociationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.AssociationService;
@Service
public class AssociationServiceIMPL implements AssociationService{
@Autowired
private AssociationRepository rep ;
@Autowired
private AccountRepository partRep;
@Autowired
private UserRepository userRep;

	@Override
	public List<Association> retriveAllAssociation() {
		
		return (List<Association>)rep.findAll();
	}

	@Override
	public Association addAssociation(Association c) {
		
		LocalDate date=LocalDate.now() ;
		c.setCreationDate(date);
		int p=c.getPlaces();
		LocalDate f= date.plusMonths(p);
		c.setFinishDate(f);
		return rep.save(c);
	}

	@Override
	public void deleteAssociation(Long id) {
		rep.deleteById(id);
		
	}

	@Override
	public Association uploadAssociation(Association c) {
		// TODO Auto-generated method stub
		return rep.save(c);
	}

	@Override
	public Association retrieveAssociationBYID(Long id) {
		// TODO Auto-generated method stub
		return rep.findById(id).get();
	}

	@Override
	public List<Association> retrieveAssociationByP(int p) {
		// TODO Auto-generated method stub
		return rep.retrieveAssociationByPlace(p);
	}

	@Override
	public List<Association> retrieveAssociationByTime() {
		// TODO Auto-generated method stub
		return (List<Association>)rep.retrieveAssociationByT();
	}

	@Override
	public void ajouterParticipant(Long associationId, Long participantId) {
		
			Association association = rep.findById(associationId).get();
			Account participant = partRep.findById(participantId).get();

			if(association.getParticipants() == null){
				List<Account> participants = new ArrayList<>();
				participants.add(participant);
				association.setParticipants(participants);
				rep.save(association);
			}else{

				association.getParticipants().add(participant);
				rep.save(association);
			}

		
		
	}

	@Override
	public List<Long> afficherParticipants(Long AssociationId) {
		
			Association association = rep.findById(AssociationId).get();
			List<Long> ParticipantId = new ArrayList<>();
			for(Account participant : association.getParticipants()){
				ParticipantId.add(participant.getIdAccount());
			}
			
			return ParticipantId;
		}

	@Override
	public List<Association> retriveAssociationByScore(Long idU) {
		int score=0;
		User user=userRep.findById(idU).get();
		LocalDate date=new java.sql.Date(user.getDateBirth().getTime()).toLocalDate();
		LocalDate now=LocalDate.now();
		long age = ChronoUnit.YEARS.between(date, now);
	 

	  if(user.getAccount().getBalance()<5000) 
		  score+=300;
	  else if(5000<user.getAccount().getBalance() && user.getAccount().getBalance()<10000)
		  score+=150;
	  else if(user.getAccount().getBalance()>25000)
		  score-=600;
	  
	  if(age<25) 
		  score+=200;
	  else if(age<35)
		  score+=100;
	  
	  if(score>=500)
	  {return  rep.score500()  ;}
	  else if(score>=350)
	  {return rep.score350()   ;}
	  else if (score<100)
		  {return  rep.scoreMin()  ;}
	  else return (List<Association>) rep.findAll();
	}
		
	
	

	/*@Override
	public void deleteSAssociationByPLandNM(int p, int nb) {
		// TODO Auto-generated method stub
		rep.deleteAssociationByPlacesAndMonths(p, nb);
	}
*/
	

/*	@Override
	public void deleteSAssociationByPLandNM(long p, long nb) {
		rep.deleteAssociationByPlacesAndMonths(p, nb);
		
	}*/
	
	

}
