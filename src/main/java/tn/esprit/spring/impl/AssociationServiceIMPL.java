package tn.esprit.spring.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entite.Association;
import tn.esprit.spring.repository.AssociationRepository;
import tn.esprit.spring.service.AssociationService;
@Service
public class AssociationServiceIMPL implements AssociationService{
@Autowired
private AssociationRepository rep ;
	@Override
	public List<Association> retriveAllAssociation() {
		
		return (List<Association>)rep.findAll();
	}

	@Override
	public Association addAssociation(Association c) {
		// TODO Auto-generated method stub
		return rep.save(c);
	}

	@Override
	public void deleteAssociation(Long id) {
		rep.deleteById(id);
		
	}

	@Override
	public Association uploadAssociation(Association c) {
		// TODO Auto-generated method stub
		return rep.save(c);
	}

	@Override
	public Association retrieveAssociationBYID(Long id) {
		// TODO Auto-generated method stub
		return rep.findById(id).get();
	}

	@Override
	public List<Association> retrieveAssociationByP(int p) {
		// TODO Auto-generated method stub
		return rep.retrieveAssociationByPlace(p);
	}

	@Override
	public List<Association> retrieveAssociationByTime() {
		// TODO Auto-generated method stub
		return (List<Association>)rep.retrieveAssociationByT();
	}

	/*@Override
	public void deleteSAssociationByPLandNM(int p, int nb) {
		// TODO Auto-generated method stub
		rep.deleteAssociationByPlacesAndMonths(p, nb);
	}
*/
	

/*	@Override
	public void deleteSAssociationByPLandNM(long p, long nb) {
		rep.deleteAssociationByPlacesAndMonths(p, nb);
		
	}*/

}
