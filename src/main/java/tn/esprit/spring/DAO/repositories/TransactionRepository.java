package tn.esprit.spring.DAO.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import tn.esprit.spring.DAO.entities.Transaction;

import java.util.Date;
import java.util.List;
import tn.esprit.spring.DAO.entities.TransactionType;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	//select
	@Query("SELECT t FROM Transaction t WHERE t.transactiontype= :transactiontype")
	List<Transaction> retrieveTransactionByTransactionType(@Param("transactiontype") TransactionType transactiontype);
	
	//update
//	@Modifying
	//@Query("update Transaction t set t.transactionType = :transactiontype where u.sumToTransfer :sumToTransfer")
	//int updateTransactionByTransactionType(@Param("transactiontype") TransactionType transactiontypecategorieTransaction, @Param("sumToTransfer") sumToTransfer sumToTransfer);

	//delete
	@Modifying
	@Query("DELETE FROM Transaction t WHERE t.transactiontype = :transactiontype")
	int deleteTransactionByTransactionType(@Param("transactiontype") TransactionType transactiontype); 
	
	//insert
	@Modifying
	@Query(value = "INSERT INTO Transaction (dateTransaction,sumToTransfer, totalSum,transactiontype) VALUES (:dateT, :sumToT, :totalSum, :transactiontype)", nativeQuery = true)
	void insertTransaction(@Param("dateT") Date dateTransaction, @Param("sumToT") int sumToTransfer,
			@Param("totalSum") int totalSum, @Param("transactiontype") TransactionType transactiontype);
	
	
	
	
	//stat
	@Query("SELECT t From Transaction t Where t.dateTransaction BETWEEN :date1 AND :date2 ORDER BY t.dateTransaction ASC")
	List<Transaction> ListTransactionOfweekAgo(@Param("date1") Date date1,@Param("date2") Date date2);
}
