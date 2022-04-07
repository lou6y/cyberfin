package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entite.Association;
import tn.esprit.spring.entite.Account;


public interface AssociationService {

	List<Association> retriveAllAssociation();
	Association addAssociation(Association c);
	void deleteAssociation(Long id);
	Association uploadAssociation(Association c);
	Association retrieveAssociationBYID(Long id);
    //void deleteSAssociationByPLandNM(int p,int nb);
	List<Association> retrieveAssociationByP(int p);
	List<Association> retrieveAssociationByTime();
	void ajouterParticipant(Long associationId, Long participantId);
	List<Long> afficherParticipants(Long AssociationId);
	List<Association> retriveAssociationByScore(Long idU);
}
