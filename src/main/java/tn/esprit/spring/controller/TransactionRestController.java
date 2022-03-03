package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.services.Interfaces.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
	
	

	@Autowired
	TransactionService transactionService;

	// http://localhost:8083/SpringMVC/transaction/retrieve-all-transactions
	@GetMapping("/retrieve-all-transactions")
	@ResponseBody
	public List<Transaction> getTransactions() {
		List<Transaction> listTransactions = transactionService.retrieveAllTransactions();
		return listTransactions;
		}
	
	// http://localhost:8083/SpringMVC/transaction/retrieve-transaction/
		@GetMapping("/retrieve-transaction/{transaction-id}")
		@ResponseBody
		public Transaction retrieveTransaction(@PathVariable("transaction-id") Long transactionId) {
		return transactionService.retrieveTransaction(transactionId);
		}

		// http://localhost:8083/SpringMVC/transaction/add-transaction
		@PostMapping("/add-transaction")
		@ResponseBody
		public Transaction addTransaction(@RequestBody Transaction c)
		{
		Transaction transaction = transactionService.addTransaction(c);
		return transaction;
		}



		// http://localhost:8083/SpringMVC/transaction/remove-transaction/{transaction-id}
		@DeleteMapping("/remove-transaction/{transaction-id}")
		@ResponseBody
		public void removeTransaction(@PathVariable("transaction-id") Long transactionId) {
		transactionService.deleteTransaction(transactionId);
		}

		// http://localhost:8083/SpringMVC/transaction/modify-transaction
		@PutMapping("/modify-transaction")
		@ResponseBody
		public Transaction modifyTransaction(@RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transaction);
		}
	

}

