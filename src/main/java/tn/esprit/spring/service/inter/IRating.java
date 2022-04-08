package tn.esprit.spring.service.inter;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.DAO.entities.Rating;
import tn.esprit.spring.DAO.entities.User;

public interface IRating {

	public  ResponseEntity ratingOffer(User user,Rating rating, Long id);
	public ResponseEntity DeleteRating(  Long id);
	public ResponseEntity EditRating(  Long id, Rating rating);
	
	public List<Rating> RetrieveRating();
	
	public List<Rating> getMyRating( User user);
	
	public List<Rating>  getRatingWithOfferId(Long id);
	
	public Double  AvgRatingByOffer(Offer id);
	
	public Integer nbrOfRatingUserByOffer(Offer id);
	
	
	
}
