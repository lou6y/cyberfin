package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.service.inter.IOfferService;

@RestController
@RequestMapping("/offer")
public class OfferRestController {

	
	@Autowired
	IOfferService OfferService;
	
	// http://localhost:8086/SpringMVC/offer/addOffer
	@PostMapping("/addOffer")
	@ResponseBody	
	public Offer addOffer(@RequestBody Offer o) {
	return OfferService.addOffer(o);}
	
	
	// http://localhost:8086/SpringMVC/offer/retrieveAllOffers
	@GetMapping("/retrieveAllOffers")
	@ResponseBody
	public List<Offer> getOffer() {
	 List<Offer> o = OfferService.retrieveAllOffers();
	 return o;
	 }
	
	
	// http://localhost:8086/SpringMVC/offer/remove-offer/{offer-id}
	@DeleteMapping("/remove-offer/{offer-id}")
	@ResponseBody
	public void removeOffer(@PathVariable("offer-id") String offerId) {
	OfferService.deleteOffer(offerId);
	}
	
	
	
	// http://localhost:8086/SpringMVC/offer/retrieve-offer/{offer-id}
	@GetMapping("/retrieve-offer/{offer-id}")
	@ResponseBody
	public Offer retrieveOffer(@PathVariable("offer-id") String offerId) {
	return OfferService.retrieveOffer(offerId);
	}
	
	
}

