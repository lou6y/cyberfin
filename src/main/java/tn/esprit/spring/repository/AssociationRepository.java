package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entite.Association;
@Repository
public interface AssociationRepository extends CrudRepository<Association,Long>{

	/*@Modifying
	@Query("DELETE a FROM Association a WHERE a.Places= ?1 And a.NbMonths = ?2")
	void deleteAssociationByPlacesAndMonths(int p int nb);
	*/
	@Query("SELECT a FROM Association a WHERE a.places= ?1")
	List<Association> retrieveAssociationByPlace(int p);
	
	@Query("select a From Association a where a.finishDate<:CURRENT_DATE")
	List<Association> retrieveAssociationByT();

}
