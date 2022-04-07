package tn.esprit.spring.DAO.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import tn.esprit.spring.DAO.entities.Transaction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;




@Transactional
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	
	//select
	@Query("SELECT t FROM Transaction t WHERE t.transaction_type= :transaction_type")
	List<Transaction> retrieveTransactionByTransactionType(@Param("transaction_type") String transaction_type);
	
	//select lel scheduled
	@Query("SELECT t FROM Transaction t WHERE t.account_id= :account_id")
	List<Transaction> retrieveTransactionByAccountid(@Param("account_id") Long account_id);
	
	//update
//	@Modifying
//	@Query("update Transaction t set t.transactionType = :transactiontype where t.sumToTransfer =:sumToTransfer")
//	int updateTransactionByTransactionType(@Param("transactiontype") TransactionType transactiontypecategorieTransaction, @Param("sumToTransfer") sumToTransfer sumToTransfer);

	//delete
	@Modifying
	@Query("DELETE FROM Transaction t WHERE t.transaction_type = :transaction_type")
	int deleteTransactionByTransactionType(@Param("transaction_type") String transaction_type); 
	
	
	

	//SERVICES:
	
//lel deposit w withdrawel
		//wHere acc.id_account (nafs l esm fel database)
		@Modifying
		    @Query(value ="UPDATE Account acc SET acc.balance = :new_balance WHERE acc.id_account = :id_account" , nativeQuery = true)
		    @Transactional
		    void changeAccountBalanceById(@Param("new_balance") double new_balance, @Param("id_account") Long id_account);
		
		
//lel deposit w transfer
			@Query(value = "SELECT balance FROM Account WHERE id_account = :id_account", nativeQuery = true)
			double getAccountBalance(@Param("id_account") Long id_account);
		

			//insert (AUDIT TRAIL)
			 @Modifying
			    @Transactional
			    @Query(value = "INSERT INTO Transaction(account_id, transaction_type, amount, source, status, reason_code, created_at)" +
			            "VALUES(:account_id, :transact_type, :amount, :source, :status, :reason_code, :created_at)", nativeQuery = true)
			    void historiqueTransaction(@Param("account_id")Long account_id,
			                        @Param("transact_type")String transact_type,
			                        @Param("amount")double amount,
			                        @Param("source")String source,
			                        @Param("status")String status,
			                        @Param("reason_code")String reason_code,
			                        @Param("created_at") LocalDateTime created_at);
			 
			 
			 @Query(value = "SELECT COUNT(t.account_id) FROM Transaction t WHERE account_id = :account_id AND transaction_type= :transaction_type AND status= :status", nativeQuery = true)
			    int getnumbertransactionsbytypeandstatus(@Param("account_id") Long account_id, @Param("transaction_type") String transaction_type, @Param("status") String status);
			 		 
		
		
	
	//stat
	@Query("SELECT t From Transaction t Where t.created_at BETWEEN :date1 AND :date2 ORDER BY t.created_at ASC")
	List<Transaction> ListTransactionOfweekAgo(@Param("date1") Date date1,@Param("date2") Date date2);
}
