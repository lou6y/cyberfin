package tn.esprit.spring.service;

import java.time.LocalDate;
import java.util.List;

import tn.esprit.spring.entite.Association;
import tn.esprit.spring.entite.PaymentAssociation;

public interface PaymentAssociationService {

	
	List<PaymentAssociation> retriveAllPayments();
	void addPayment(Long id);
	void deletePayment(Long id);
	PaymentAssociation uploadPayment(PaymentAssociation p);
	PaymentAssociation retrievePaymentId(Long id);
	void addReservation(Long id);
	List<PaymentAssociation> getReservation(Long id);
	void MakeReservation(Long idp,Long ida,Long idc);
	void Pay(Long idp);
	void Penality(Long idp);
	List<Association>nonpayee();
	List<Long> suseptible();
}
