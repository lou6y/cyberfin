package tn.esprit.spring.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.DAO.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	
	
	@Query("SELECT AVG(r.value) FROM Rating r WHERE r.offer like :id")
	//@Query("SELECT AVG(r.value) FROM Rating r ")
	public Double  AvgRatingByOffer(@Param("id")Offer id);
	
	
	@Query("SELECT COUNT(r.user) FROM Rating r WHERE r.offer like :id")
	//@Query("SELECT AVG(r.value) FROM Rating r ")
	public Integer nbrOfRatingUserByOffer(@Param("id")Offer id);
	
	
	
	
	
}
