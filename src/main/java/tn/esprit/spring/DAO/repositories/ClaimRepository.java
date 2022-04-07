package tn.esprit.spring.DAO.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DAO.entities.Claim;
import tn.esprit.spring.DAO.entities.ClaimInfo;
import tn.esprit.spring.DAO.entities.Status;
import tn.esprit.spring.DAO.entities.Transaction;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Transactional
@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long> {

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
	 
	 
	//update claim b acocunt_id
		@Modifying
		@Query("update Claim c set c.state = :state where c.claim_id =:claim_id")
		int updateClaimByClaimId(@Param("state") String state, @Param("claim_id") Long claim_id);
		
		
		//NHEB NCHOUF CLAIM TEB3A L ENA ACCOUNTID + l ena userid w lena transaction type
		////T3ADIT B CLAIM,TRANSACTION, ACCOUNT ,USER
		@Query("select new tn.esprit.spring.DAO.entities.ClaimInfo(c.created_at, a.idAccount , a.Balance, t.transaction_type,u.id) from Claim c inner join Account a on c.account_id = a.idAccount inner join Transaction t on c.status = t.status inner join User u on a.idAccount = u.id ORDER BY a.idAccount")
		public List<ClaimInfo> getClaimInfoWithConstrutorExp();
		
		 
		@Query(value = "SELECT COUNT(ci.aid_account) FROM Claim_info ci WHERE aid_account = :aid_account AND ttypetransaction= :ttypetransaction", nativeQuery = true)
	    int getnumberclaimsbytype(@Param("aid_account") Long aid_account, @Param("ttypetransaction") String ttypetransaction);
	 

	
		
}
