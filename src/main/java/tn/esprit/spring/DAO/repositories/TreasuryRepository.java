package tn.esprit.spring.DAO.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.DAO.entities.Treasury;

@Transactional
@Repository
public interface TreasuryRepository  extends CrudRepository<Treasury, Long>{
	
	//na7itelhom el : ba3d l where bech khedmet bech khdhehech el treasuryamount ka parametre
	@Modifying
    @Query(value ="UPDATE Treasury tr SET tr.treasuryamount = :new_treasuryamount WHERE tr.treasuryamount = treasuryamount" , nativeQuery = true)
    
	@Transactional
    void changeTreasuryAmount(@Param("new_treasuryamount") double new_treasuryamount);
	
	@Transactional
	@Query(value = "SELECT treasuryamount FROM Treasury WHERE treasuryamount = treasuryamount", nativeQuery = true)
	double getTreasuryAmount(@Param("treasuryamount")double treasuryamount);

	
}
