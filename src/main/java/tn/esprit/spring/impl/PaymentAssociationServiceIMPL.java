package tn.esprit.spring.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entite.Account;
import tn.esprit.spring.entite.Association;
import tn.esprit.spring.entite.PaymentAssociation;
import tn.esprit.spring.repository.AccountRepository;
import tn.esprit.spring.repository.AssociationRepository;
import tn.esprit.spring.repository.PaymentAssociationRepository;
import tn.esprit.spring.service.PaymentAssociationService;
@Service
public class PaymentAssociationServiceIMPL implements PaymentAssociationService {

	@Autowired
	PaymentAssociationRepository payrep;
	@Autowired
	AssociationRepository arep;
	@Autowired
	AccountRepository acrep;
	
	@Override
	public List<PaymentAssociation> retriveAllPayments() {
		// TODO Auto-generated method stub
		return (List<PaymentAssociation>)payrep.findAll();
	}

	@Override
	public void addPayment(Long id) {
		Association a= arep.findById(id).get();
		for (int j=0;j<a.getPlaces();j++) {
		  for (int i=0;i<a.getPlaces();i++)
		{
			
		PaymentAssociation pa=new PaymentAssociation();
		
		LocalDate date= a.getCreationDate().plusMonths(i);
		pa.setPaymentDate(date);
		pa.setAssociationP(a);
		  payrep.save(pa);
		}
		
		}
	}
		
	

	@Override
	public void deletePayment(Long id) {
		// TODO Auto-generated method stub
		payrep.deleteById(id);
	}

	@Override
	public PaymentAssociation uploadPayment(PaymentAssociation p) {
		// TODO Auto-generated method stub
		return payrep.save(p);
	}

	@Override
	public PaymentAssociation retrievePaymentId(Long id) {
		// TODO Auto-generated method stub
		return payrep.findById(id).get();
	}

	@Override
	public void addReservation(Long id) {
	 
		
	}

	@Override
	public List<PaymentAssociation> getReservation(Long id) {
		
		return (List<PaymentAssociation>)payrep.getReservationJPQL(id);
	}

	@Override
	public void MakeReservation(Long idp, Long ida,Long idc) {
		Association a= arep.findById(ida).get();
		
		PaymentAssociation pa=payrep.findById(idp).get();
		pa.setAssociationP(a);
		pa.setStatus(true);
		pa.setClient(idc);
		payrep.save(pa);
		
		
	}

	@Override
	public void Pay(Long idp) {
		PaymentAssociation pa=payrep.findById(idp).get();
		boolean v=pa.getStatus();
		if(v=false) {
		pa.setValidPayment(true);
		payrep.save(pa);
		}
		else { 
			Long c=pa.getClient();
			float payment=(float) (pa.getAssociationP().getSum()-pa.getAssociationP().getSum()*(pa.getAssociationP().getIntrests()/100));
			Account a=acrep.findById(c).get();
			
			Long ba=(long) (a.getBalance()+payment);
			a.setBalance((long) ba);
			acrep.save(a);
		}
		
		
	}

	@Override
	@Transactional
	public void Penality(Long idp) {
		float p=0;
		PaymentAssociation pa=payrep.findById(idp).get();
		Long c=pa.getClient();
		
		int nbp=payrep.getUnvalid(c);
		
		float s=pa.getAssociationP().getSumToPay();
		int i=1;
	   while ( i<nbp) {
		    p+=((i+2)/100)*s+s*i;
		    i++;
		    
	   }
		
		Account a=acrep.findById(c).get();
		float b=a.getBalance();
		float n=b-p;
		a.setBalance((long) n);
		
		
		
	}

	@Override
	public List<Association> nonpayee() {
		
		return payrep.GetHarraba();
	}

	@Override
	public List<Long> suseptible() {
		
		return payrep.GetSuseptible2();
	}
	
	

}
