package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entite.Association;
@Repository
public interface AssociationRepository extends CrudRepository<Association,Long>{

}
