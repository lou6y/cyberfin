package tn.esprit.spring.DAO.repositories;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.DAO.entities.Status;

import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long> {
	
	
/*	idClaim, description, status;  */
	
	//insert (AUDIT TRAIL)
	 @Modifying
	    @Transactional
	    @Query(value = "INSERT INTO Claim(account_id, transaction_type, amount, source, status, reason_code, created_at)" +
	            "VALUES(:account_id, :transact_type, :amount, :source, :status, :reason_code, :created_at)", nativeQuery = true)
	    void historiqueTransaction(@Param("account_id")Long account_id,
	                        @Param("transact_type")String transact_type,
	                        @Param("amount")double amount,
	                        @Param("source")String source,
	                        @Param("status")String status,
	                        @Param("reason_code")String reason_code,
	                        @Param("created_at") LocalDateTime created_at);

}
