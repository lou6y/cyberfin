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

}
