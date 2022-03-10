package tn.esprit.spring.services.Interfaces;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.DAO.entities.Transaction;


public interface TransactionService {
	
	List<Transaction> retrieveAllTransactions();

	Transaction addTransaction(Transaction t);

	void deleteTransaction(Long id);

	Transaction updateTransaction(Transaction t);
	Transaction retrieveTransaction(Long id);
	
	public Map<String, Integer> NbrTransactionWeek();

}
