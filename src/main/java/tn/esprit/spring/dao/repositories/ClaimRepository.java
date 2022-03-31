package tn.esprit.spring.dao.repositories;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Claim;
import tn.esprit.spring.dao.entities.Status;
import tn.esprit.spring.dao.entities.Transaction;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Transactional
@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long> {
	
	
/*	private Long claim_id;
	
	@JsonBackReference
	@ManyToOne
	Transaction transaction;
	private Long account_id;
    private String transaction_type;
    private double amount;
    private String source;
    private String status;
    private String reason_code;
    private LocalDateTime created_at;
    private String state;  */
	
	//insert (AUDIT TRAIL)
	 @Modifying
	    @Transactional
	    @Query(value = "INSERT INTO Claim(transaction_transaction_id, account_id,transaction_type ,amount, source, status, reason_code, created_at, state)" +
	            "VALUES(:transaction_transaction_id, :account_id, :transact_type, :amount, :source, :status, :reason_code, :created_at, :state)", nativeQuery = true)
	    public void failedTransaction(
	    					@Param("transaction_transaction_id")Long transaction_transaction_id,
	    					@Param("account_id")Long account_id,
	                        @Param("transact_type")String transact_type,
	                        @Param("amount")double amount,
	                        @Param("source")String source,
	                        @Param("status")String status,
	                        @Param("reason_code")String reason_code,
	                        @Param("created_at") LocalDateTime created_at,
	                        @Param("state")String state);
	 
	//bech najamt nrecuperi el transaction_id mta3 el claim fel transactionrestcontroller
	 //findtransacitonidbycreattime
	 @Query(value = "SELECT transaction_id FROM Transaction WHERE created_at = :created_at", nativeQuery = true)
		Long findTopByOrderByTransactionIdDesc(@Param("created_at") LocalDateTime created_at);

}
