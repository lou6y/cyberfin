package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	

}

