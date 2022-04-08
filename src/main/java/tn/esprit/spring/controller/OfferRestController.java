package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.entities.Offer;
import tn.esprit.spring.DAO.entities.User;
import tn.esprit.spring.helper.offerExcelExporter;
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
	
	
	// http://localhost:8086/SpringMVC/offer/modifyOffer
		@PostMapping("/modifyOffer")
		@ResponseBody	
		public Offer ModifyOffer(@RequestBody Offer o) {
		return OfferService.modifyOffer(o);}
	
	
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
	
	
	

	
	
	//http://localhost:8086/SpringMVC/offer/affOfferUser/{idUser}/{idOffer}
	@PostMapping("/affOfferUser/{idUser}/{idOffer}")
	@ResponseBody
	  public void affecterUserOffer(@PathVariable("idUser")Long iduser,@PathVariable("idOffer")Long idoffer) {
		OfferService.affecterUserOffer(iduser, idoffer);
	  }
	
	
	
	
	
	
	//http://localhost:8086/SpringMVC/offer/mostUsedType
	@GetMapping("/mostUsedType")
	public String mostUsedOff(){

		return OfferService.mostUsedOff();
	}
	
	/* @Scheduled(fixedRate=60000)*/
		@DeleteMapping("/deleteOldOffers")
		@ResponseBody
		public void deleteExpiredOffre() {
			
		 OfferService.deleteoldoffer();
		}
	
	 /*@PostMapping("/participation/{idUser}/{idOffer}")
	 @ResponseBody
	 public int Participation(@PathVariable (value ="idUser") long idUser,@PathVariable (value ="idOffer")long idOffer) throws IOException, MessagingException
	 {  User user = userRepository.findById(idUser).orElse(null);
		emailSendService.sendEmail("meriam.seddik@esprit.tn", "participation",user.getUserName(),user);
		return OfferService.participation(idUser, idOffer);
	 }*/
	 
	//http://localhost:8086/SpringMVC/offer/export/excel
	 @GetMapping("/export/excel")
		public void exportToExcel(HttpServletResponse response) throws IOException {
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        
			
			String headervalue = "attachment; filename=ListOffersByRating.xlsx";
			
			

			response.setHeader(headerKey, headervalue);
			List<Offer> listOffers = OfferService.listAll();
			offerExcelExporter exp = new offerExcelExporter(listOffers);
			exp.export(response);

		}
	 
	//http://localhost:8086/SpringMVC/offer/ListOfOffer
	 @GetMapping("/ListOfOffer")
	 public List<Offer> listAll() {
			return 	OfferService.listAll();
		}
	 
	 
	 
	 
	
}

