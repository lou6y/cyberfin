package tn.esprit.spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.util.RequestPayload;

import tn.esprit.spring.entite.Association;
import tn.esprit.spring.entite.PaymentAssociation;
@Repository
public interface PaymentAssociationRepository extends CrudRepository<PaymentAssociation, Long> {

	
/*	@Query("SELECT a.idAccount From Account a"
			+ " Join a.association.payment p "
			+ " Where p.status=0 "
			+ "And p.validPayment=0"
			+ " And p.paymentDate<=CURRENT_DATE")
    List<Long>GetHarrabaDate();*/
	
	
	@Query("SELECT a.associationP From PaymentAssociation a Where a.status=0 And a.validPayment=0 And a.paymentDate<=CURRENT_DATE")
    List<Association>GetHarraba();
	
	@Query("SELECT p.client From PaymentAssociation p Where p.status=0 And p.validPayment=0")
     List<Long>getHarraba();
	
	@Query("SELECT p FROM PaymentAssociation p WHERE p.associationP.id_association= :id AND p.status= 0")
	List<PaymentAssociation> getReservationJPQL(@Param("id") Long
	id);
	
	@Query("SELECT COUNT(p) FROM PaymentAssociation p WHERE  p.validPayment=0 AND p.client=:id ")
	int getUnvalid(@Param("id") Long id);
	
	/*@Query("SELECT a.idAccount From Account a "
			
			+ "Where a.Balance<=(a.association.sumToPay) * 3 ")
	List<Long> GetSuseptible();*/
	
@Query("SELECT p.client From PaymentAssociation p "
		+ "Join p.associationP.participants  pr "
			
			+ "Where pr.Balance<=(p.associationP.sumToPay) * 3 ")
	List<Long> GetSuseptible2();
	
}
