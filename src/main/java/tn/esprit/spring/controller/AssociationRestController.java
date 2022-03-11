package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entite.Association;
import tn.esprit.spring.service.AssociationService;


@RestController
public class AssociationRestController {

	 AssociationService associationservice;
	  
		// http://localhost:8081/CyberFin/association/retrieve-all-association
		@GetMapping("/retrieve-all-association")
		@ResponseBody
		public List<Association> getClient() {
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
					Association association=associationservice.addAssociation(c);
					
				return association;
				}
	  
				
				
				// http://localhost:8081/CyberFin/association/remove-association/{association-id}
				@DeleteMapping("/remove-association/{association-id}")
				@ResponseBody
				public void removeTransaction(@PathVariable("association-id") Long id) {
					associationservice.deleteAssociation(id);
				}
				
				//http://localhost:8083/SpringMVC/association/modify-association
					@PutMapping("/modify-association")
					@ResponseBody
					public Association modifyTransaction(@RequestBody Association a) {
					return associationservice.uploadAssociation(a);
					}
				
				/*// http://localhost:8081/SpringMVC/client/retrieve-by-profession
				@GetMapping("/retrieve-by-profession/{prof}")
				@ResponseBody
				public List<Association> getClientByProf(@Param("prof")Profession profession) {
					List<Association> listClient = associationservice.retriveByProf(profession);
					return listClient;
					}*/
		
	
	
}
