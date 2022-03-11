package tn.esprit.spring.services.Implementations;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.entities.TransactionType;
import tn.esprit.spring.DAO.repositories.TransactionRepository;
import tn.esprit.spring.services.Interfaces.TransactionService;

import java.text.SimpleDateFormat;
import java.util.*;

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
	
	@Override
	public Map<String, Integer> NbrTransactionWeek(){
		
	        Map<String, Integer> mapTransactweek = new HashMap<String, Integer>();
	        Date date = new Date(System.currentTimeMillis());
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, -7);
	        Date date1 = cal.getTime();
	        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	        System.out.println(formater.format(date)+"********"+formater.format(date1));
	        List<Transaction> listTransaction =  TransactionRep.ListTransactionOfweekAgo(date1,date);
	        for(Transaction t : listTransaction) {
	            System.out.println(t.getDateTransaction());
	            if(!mapTransactweek.containsKey(formater.format(t.getDateTransaction()))) {
	            	mapTransactweek.put(formater.format(t.getDateTransaction()), 0);

	            }
	            if(mapTransactweek.containsKey(formater.format(t.getDateTransaction()))) {
	            	mapTransactweek.put(formater.format(t.getDateTransaction()),mapTransactweek.get(formater.format(t.getDateTransaction()))+1 );
	            }
	        }
	        return mapTransactweek;
	    }

	//SELECT
	@Override
	public List<Transaction> retrieveTransactionByTransactType(TransactionType transactiontype) {
		return TransactionRep.retrieveTransactionByTransactionType(transactiontype);
	}
	
	
	//UPDATE
	//@Override
	//public int updateTransactionByTransactType(TransactionType transactiontype) {
	//	return TransactionRep.updateTransactionByTransactionType(transactiontype);
	//}
	
	
	//DELETE
	@Override
	public int deleteTransactionByTransactType(TransactionType transactiontype) {
		return TransactionRep.deleteTransactionByTransactionType(transactiontype);
	}
	
	//INSERT
	public void insertTransact(Date dateTransaction, int sumToTransfer,int totalSum, TransactionType transactiontype) {
		TransactionRep.insertTransaction(dateTransaction,sumToTransfer,totalSum, transactiontype);
	}
	
		
	

}
