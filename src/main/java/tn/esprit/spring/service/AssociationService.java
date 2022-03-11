package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entite.Association;



public interface AssociationService {

	List<Association> retriveAllAssociation();
	Association addAssociation(Association c);
	void deleteAssociation(Long id);
	Association uploadAssociation(Association c);
	Association retrieveAssociationBYID(Long id);
	
}
