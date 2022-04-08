package tn.esprit.spring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.DAO.repository.UserRepository;
import tn.esprit.spring.service.inter.IRating;
import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.DAO.entities.Rating;
import tn.esprit.spring.DAO.entities.User;
import tn.esprit.spring.DAO.repository.OfferRepository;
import tn.esprit.spring.DAO.repository.RatingRepository;



@Service

public class RatingImpl implements IRating{
	
	@Autowired 
	RatingRepository RatingRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OfferRepository OfferRepository;
	
	Double totale=5.0 ;
	
	@Override
	@Transactional
	
	public ResponseEntity ratingOffer(User user,Rating rating, Long id  ) {
		
		
		List<Offer> offers = (List<Offer>) OfferRepository.findAll();
		List<Rating> ratings = (List<Rating>) RatingRepository.findAll();
		Offer offe = OfferRepository.findById(id).get();
        for (Offer o : offers) {
			for (Rating r : ratings) {
				if ((r.getOffer().equals(offe))) {
					if (r.getUser().equals(user))
					{
				         
						r.setValue(rating.getValue());
						offe.setRatingAvg(this.RatingRepository.AvgRatingByOffer(offe ));
						offe.setCountUser(this.RatingRepository.nbrOfRatingUserByOffer(offe));
						OfferRepository.save(offe);
					
						
						return new ResponseEntity<String>("Rated already  edited !  ", HttpStatus.OK);
					}
						
				}
			}
			if (o.getIdOffer().equals(id)) {
				rating.setOffer(o);
				rating.setUser(user);
				
                RatingRepository.save(rating);
				userRepository.save(user);
				offe.setRatingAvg(this.RatingRepository.AvgRatingByOffer(offe ));
				offe.setCountUser(this.RatingRepository.nbrOfRatingUserByOffer(offe));
				OfferRepository.save(offe);
				return new ResponseEntity<String>("offer was rated successfully!", HttpStatus.OK);
		}

		}

		return new ResponseEntity<String>("Offer was not rated!", HttpStatus.BAD_REQUEST);
			
		}
	
	
	@Override
	public ResponseEntity EditRating( Long id, Rating rating) {
		List<Rating> ratings = (List<Rating>) RatingRepository.findAll();
		for(Rating r : ratings)
		{
			 if(r.getIdRating().equals(id)){
				 r.setValue(rating.getValue());
				 
				 
				 RatingRepository.save(r);
				 return new ResponseEntity<String>("Offer rating edited successfully!",HttpStatus.OK);

			 }

		}
		return new ResponseEntity<String>("Failed to edit Offer rating!",HttpStatus.BAD_REQUEST);
	
	}

	@Override
	public ResponseEntity DeleteRating( Long id) {
		List<Rating> ratings = (List<Rating>) RatingRepository.findAll();
		for(Rating r : ratings)
		{
			 if(r.getIdRating().equals(id)){
				
				 RatingRepository.deleteById(id);
				 return new ResponseEntity<String>("Rating deleted successfully!",HttpStatus.OK);

			 }

		}
		return new ResponseEntity<String>("Failed to delete Offer rating!",HttpStatus.BAD_REQUEST);
	}

    @Override
	public List<Rating> RetrieveRating() {
		return 	 (List<Rating>) RatingRepository.findAll();
	}


	@Override
	public List<Rating> getMyRating(User user) {
		List<Rating> ratings = (List<Rating>) RatingRepository.findAll();
		List<Rating> myRatings = new ArrayList<Rating>();
		for (Rating r : ratings) {
			if (r.getUser().equals(user)) {
				myRatings.add(r);
			}
		}
		return myRatings;

	}


	@Override
	public List<Rating> getRatingWithOfferId(Long id) {
		List<Offer> offers = (List<Offer>) OfferRepository.findAll();
		List<Rating> ratings = (List<Rating>) RatingRepository.findAll();
		Offer offer = OfferRepository.findById(id).get();
		List<Rating> myRatings = new ArrayList<Rating>();
        
			for (Rating r : ratings) {
				if ((r.getOffer().equals(offer))) {
					
					myRatings.add(r);	
				}
			}
		
        return myRatings;

	}


	@Override
	public Double  AvgRatingByOffer(Offer id  ) {
		//Double  df = new DecimalFormat("#.####");
		 //	 df=ratingRepository.AvgRatingByOffer(id);
		 	///return df.setRoundingMode(RoundingMode.CEILING);
		return RatingRepository.AvgRatingByOffer(id);
		
		
	}


	@Override
	public Integer nbrOfRatingUserByOffer(Offer id) {
		// TODO Auto-generated method stub
		return RatingRepository.nbrOfRatingUserByOffer(id);
	}
	
	
	

}
