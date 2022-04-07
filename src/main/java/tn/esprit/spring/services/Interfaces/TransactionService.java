package tn.esprit.spring.services.Interfaces;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestParam;

import tn.esprit.spring.DAO.entities.Transaction;


public interface TransactionService {
	
	List<Transaction> retrieveAllTransactions();
	Transaction retrieveTransaction(Long id);
	Transaction addTransaction(Transaction t);
	void deleteTransaction(Long id);
	Transaction updateTransaction(Transaction t);
	
	public Map<String, Integer> NbrTransactionWeek();
	
	List<Transaction> retrieveTransactionByTransactType(String transaction_type);
	//int updateTransactionByTransactType(TransactionType transactiontype);
	void deleteTransactionByTransactType(String transaction_type);
//insert
	void historiqueTransact( Long account_id,String transaction_type, double amount,String source,String status, String reason_code,LocalDateTime created_at);

	//lel scheduled
	List<Transaction> retrieveTransactionByAccid(Long account_id);
	
	


		  
	
}
