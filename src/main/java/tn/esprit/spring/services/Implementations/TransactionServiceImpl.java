package tn.esprit.spring.services.Implementations;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.repositories.TransactionRepository;
import tn.esprit.spring.services.Interfaces.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionRepository TransactionRep;
	
	@Override
	public List<Transaction> retrieveAllTransactions() {
		List<Transaction> transactions= (List<Transaction>)(TransactionRep.findAll());
		//traja3ali de type iterable , donc nrodha traja3 type client
		return transactions;
		
	}

	@Override
	public Transaction addTransaction(Transaction t) {
		return TransactionRep.save(t);
	}

	@Override
	public void deleteTransaction(Long id) {
		TransactionRep.deleteById(id);
	}

	@Override
	public Transaction updateTransaction(Transaction t) {
		return TransactionRep.save(t);
	}

	@Override
	public Transaction retrieveTransaction(Long id) {
		return TransactionRep.findById(id).get();
	}

}
