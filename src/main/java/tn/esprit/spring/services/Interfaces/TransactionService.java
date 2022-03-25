package tn.esprit.spring.services.Interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.entities.TransactionType;


public interface TransactionService {
	
	List<Transaction> retrieveAllTransactions();

	Transaction addTransaction(Transaction t);

	void deleteTransaction(Long id);

	Transaction updateTransaction(Transaction t);
	Transaction retrieveTransaction(Long id);
	
	public Map<String, Integer> NbrTransactionWeek();
	
	List<Transaction> retrieveTransactionByTransactType(TransactionType transactiontype);
	//int updateTransactionByTransactType(TransactionType transactiontype);
	void deleteTransactionByTransactType(TransactionType transactiontype);
	
	void insertTransact(Date dateTransaction, int sumToTransfer,int totalSum, TransactionType transactiontype);

}
