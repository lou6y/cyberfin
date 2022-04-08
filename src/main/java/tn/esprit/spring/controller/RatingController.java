package tn.esprit.spring.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.repository.UserRepository;
import tn.esprit.spring.service.inter.IRating;
import tn.esprit.spring.DAO.entities.Rating;
import tn.esprit.spring.DAO.entities.User;
import tn.esprit.spring.DAO.entities.Offer;


@RestController
	@RequestMapping("/api/rating")
	
	public class RatingController {
		
		@Autowired
		IRating ratingService;
		@Autowired
		UserRepository userRepository;
		// http://localhost:8086/SpringMVC/api/rating/ratingOffer/{id}/{username}
		@PostMapping("/ratingOffer/{id}/{username}")
		public ResponseEntity ratingOffer(@PathVariable("id") Long id ,@RequestBody Rating rating,@PathVariable(value="username") String username) {
			User us = userRepository.findByUserName(username);
			return ratingService.ratingOffer(us, rating, id);
		}
		
		// http://localhost:8086/SpringMVC/api/rating/editRating/{id}
		@PutMapping("/editRating/{id}")
		public ResponseEntity EditRating(@PathVariable("id") Long id, @RequestBody Rating rating) {
			
			return ratingService.EditRating( id, rating);

		}
		// http://localhost:8086/SpringMVC/api/rating/DeleteRating/{id}
		@DeleteMapping("/DeleteRating/{id}")
		public ResponseEntity DeleteRating(@PathVariable("id") Long id) {
			
			return ratingService.DeleteRating( id);
		}
		// http://localhost:8086/SpringMVC/api/rating/ListOfOfferRating
		@GetMapping("/ListOfOfferRating")
		public List<Rating> RetrieveRating() {
			return 	ratingService.RetrieveRating();
		}
		// http://localhost:8086/SpringMVC/api/rating/AvgRatingByOffer/{id}
					@GetMapping("/AvgRatingByOffer/{id}")
					public Double  AvgRatingByOffer(@PathVariable("id") Offer id) {
						return ratingService.AvgRatingByOffer(id);
					}
		
		// http://localhost:8086/SpringMVC/api/rating/ListOfMyOfferRating/{username}
		@GetMapping("/ListOfMyOfferRating/{username}")
		public List<Rating> getMyRating(@PathVariable(value="username") String username) {
			
			User us = userRepository.findByUserName(username);
			return ratingService.getMyRating(us);
		}

		
		
		
		// http://localhost:8086/SpringMVC/api/rating/getRatingWithOfferId/{id}
			@GetMapping("/getRatingWithOfferId/{id}")
			public List<Rating> getRatingWithOfferId(@PathVariable("id") Long id) {
				return ratingService.getRatingWithOfferId(id);
			}
			
			
			
			// http://localhost:8086/SpringMVC/api/rating/nbrOfRatingUserByOffer/{id}
			@GetMapping("/nbrOfRatingUserByOffer/{id}")
			public Integer nbrOfRatingUserByOffer(@PathVariable("id") Offer id) {
				return ratingService.nbrOfRatingUserByOffer(id);
			}

			
	
	
	
}
