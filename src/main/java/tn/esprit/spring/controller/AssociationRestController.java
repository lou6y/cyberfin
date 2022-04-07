package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entite.Association;
import tn.esprit.spring.service.AssociationService;


@RestController
@RequestMapping("/association")
public class AssociationRestController {
@Autowired
	 AssociationService associationservice;
	  
		// http://localhost:8081/CyberFin/association/retrieve-all-association
		@GetMapping("/retrieve-all-association")
		@ResponseBody
		public List<Association> getAssociation() {
			List<Association> listassociation = associationservice.retriveAllAssociation();
			return listassociation;
			}

		
		// http://localhost:8081/CyberFin/association/retrieve-association/1
				@GetMapping("/retrieve-association/{association-id}")
				@ResponseBody
				public Association retrieveAssociationID(@PathVariable("association-id") Long id) {
				return associationservice.retrieveAssociationBYID(id);
				}
		
		
		
		
		// http://localhost:8081/CyberFin/association/add-association
				@PostMapping("/add-association")
				@ResponseBody
				public Association ajoutAssociation(@RequestBody Association c)
				
				{
					return associationservice.addAssociation(c);
					
				
				}
	  
				
				
				// http://localhost:8081/CyberFin/association/remove-association/{association-id}
				@DeleteMapping("/remove-association/{association-id}")
				@ResponseBody
				public void removeAssociation(@PathVariable("association-id") Long id) {
					associationservice.deleteAssociation(id);
				}
				
				//http://localhost:8081/CyberFin/association/modify-association
				@PutMapping("/modify-association")
				@ResponseBody
				public Association modifyAssociation(@RequestBody Association c) {
				return associationservice.uploadAssociation(c);
				}
				
			/*	// http://localhost:8081/CyberFin/association/remove-association/{association-p}/{association-nb}
				@DeleteMapping("/remove-association/{association-p}/{association-nb}")
				@ResponseBody
				public void removeAssociationByPlacesAndMonths(@PathVariable("association-p") int p,@PathVariable("association-nb") int nb) {
					associationservice.deleteSAssociationByPLandNM(p, nb);
				}*/
				
				// http://localhost:8081/CyberFin/association/retrieve-associationP/{association-p}
					@GetMapping("/retrieve-associationP/{association-p}")
					@ResponseBody
					public List<Association> afficheAssociationPlaces(@PathVariable("association-p") int p) {
					return associationservice.retrieveAssociationByP(p);
					}
				
				
				
				// http://localhost:8081/CyberFin/association/retrieve-by-t
				@GetMapping("/retrieve-by-t")
				@ResponseBody
				public List<Association> getRemainingAssociation() {
					List<Association> association = associationservice.retrieveAssociationByTime();
					return association;
					}
				
				//http://localhost:8081/CyberFin/association/add-participant/1/1
				@PutMapping("/add-participant/{association-id}/{account-id}")
				@ResponseBody
				public void addParticipant(@PathVariable("association-id") Long AssociationId,@PathVariable("account-id") Long ParticipantId) {
				associationservice.ajouterParticipant(AssociationId, ParticipantId);
				}
				
				// http://localhost:8081/CyberFin/association/retrieve-participants/1
				@GetMapping("/retrieve-participants/{association-id}")
				@ResponseBody
				public List<Long> getParticipants(@PathVariable("association-id") Long AssociationId)
				{
						List<Long> participants = associationservice.afficherParticipants(AssociationId);
					return participants;
					}
				
				// http://localhost:8081/CyberFin/association/retrieve-associationScore/{user-id}
				@GetMapping("/retrieve-associationScore/{user-id}")
				@ResponseBody
				public List<Association> afficheAssociationScore(@PathVariable("user-id") Long id) {
				return associationservice.retriveAssociationByScore(id);
				}
				
		
	
	
}
