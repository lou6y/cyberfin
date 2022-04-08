package tn.esprit.spring.dao.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Association;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long>{

	@Query("SELECT Count(a) FROM Association a WHERE a.CreationDate > :date AND a.accountassociation = :accountid")
	int NbAssociationLastdate(@Param("date") Date date, @Param("accountid") Long accountid);	
}
