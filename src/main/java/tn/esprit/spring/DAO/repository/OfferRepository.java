package tn.esprit.spring.DAO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.entities.User;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{


	
	

	
	
	@Transactional
	@Modifying
	@Query("delete from Offer o where datefin < CURRENT_DATE")
	void deleteoldoffer();
	
	//@Query("select o  from Offer o order by c.ratings.value ")
	//List<Offer> findOffers();
	
	
	
	

	
}