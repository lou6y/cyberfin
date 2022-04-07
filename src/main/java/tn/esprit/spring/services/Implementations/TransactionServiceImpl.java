package tn.esprit.spring.services.Implementations;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.repositories.TransactionRepository;
import tn.esprit.spring.services.Interfaces.TransactionService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
	public List<Transaction> retrieveTransactionByAccid(Long account_id) {
		return TransactionRep.retrieveTransactionByAccountid(account_id);
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
	            System.out.println(t.getCreated_at());
	            if(!mapTransactweek.containsKey(formater.format(t.getCreated_at()))) {
	            	mapTransactweek.put(formater.format(t.getCreated_at()), 0);

	            }
	            if(mapTransactweek.containsKey(formater.format(t.getCreated_at()))) {
	            	mapTransactweek.put(formater.format(t.getCreated_at()),mapTransactweek.get(formater.format(t.getCreated_at()))+1 );
	            }
	        }
	        return mapTransactweek;
	    }

	//SELECT
	@Override
	public List<Transaction> retrieveTransactionByTransactType(String transaction_type) {
		return TransactionRep.retrieveTransactionByTransactionType(transaction_type);
	}
	
	
	//UPDATE
	//@Override
	//public int updateTransactionByTransactType(TransactionType transactiontype) {
	//	return TransactionRep.updateTransactionByTransactionType(transactiontype);
	//}
	
	
	//DELETE
	@Override
	public void deleteTransactionByTransactType(String transaction_type) {
		 TransactionRep.deleteTransactionByTransactionType(transaction_type);
	}
	
	//INSERT
	public void historiqueTransact( Long account_id,String transaction_type, double amount,String source,String status, String reason_code,LocalDateTime created_at){		
		TransactionRep.historiqueTransaction(account_id,transaction_type,amount, source, status, reason_code, created_at);
	} 
	
		
	

}
