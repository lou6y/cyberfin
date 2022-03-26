package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.entities.Account;
import tn.esprit.spring.DAO.entities.TransactionType;
import tn.esprit.spring.services.Interfaces.TransactionService;

import tn.esprit.spring.DAO.repositories.AccountRepository;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
	
	
//AUTOWIRED kol manji nesta3mel service
	@Autowired
	TransactionService transactionService;
	
	@Autowired 
	AccountRepository accountRepository;

	// http://localhost:8083/SpringMVC/transaction/retrieve-all-transactions
	@ApiOperation(value = "afficher transactions")
	@GetMapping("/retrieve-all-transactions")
	@ResponseBody
	public List<Transaction> getTransactions() {
		List<Transaction> listTransactions = transactionService.retrieveAllTransactions();
		return listTransactions;
		}
	
	// http://localhost:8083/SpringMVC/transaction/retrieve-transaction/1
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



		//http://localhost:8083/SpringMVC/transaction/modify-transaction
		@PutMapping("/modify-transaction")
		@ResponseBody
		public Transaction modifyTransaction(@RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transaction);
		}
		
		
		// http://localhost:8083/SpringMVC/transaction/remove-transaction/{transaction-id}
		@DeleteMapping("/remove-transaction/{transaction-id}")
		@ResponseBody
		public void removeTransaction(@PathVariable("transaction-id") Long transactionId) {
		transactionService.deleteTransaction(transactionId);
		}
		
		
		//SELECT  TEKHDEM
		@GetMapping("/selecttransactionJPQL/{transactiontype}")
		@ResponseBody
		public List<Transaction> selectTransactionByTransactType(@PathVariable TransactionType transactiontype)
	    {
	        return transactionService.retrieveTransactionByTransactType(transactiontype) ;
	    }
		
		

		//INSERT   ERR
				@PostMapping("/inserttransactionJPQL/{dateT}/{sumToT}/{totalSum}/{transactiontype}")
				@ResponseBody
				public void insertTransactionByTransactType(@PathVariable @DateTimeFormat Date dateTransaction, @PathVariable int sumToTransfer,@PathVariable int totalSum, @PathVariable TransactionType transactiontype)
			    {
			         transactionService.insertTransact(dateTransaction,sumToTransfer,totalSum, transactiontype) ;
			    }
				
				
		//DELETE  //modify zeda @transactional
		//ZEDet @TRANSACTIONAL fel REPOSITORY BECH NAJAMT 5ADAMT EL UPDATE wel DELETE sinon y9oli Spring JPA - javax.persistence.TransactionRequiredException: Executing an update/delete query
		//@Transactional
				@DeleteMapping("/deletetransactionJPQL/{transactiontype}")
				@ResponseBody
				public void delTransactionByTransactType(@PathVariable TransactionType transactiontype)
			    {
			        transactionService.deleteTransactionByTransactType(transactiontype) ;
			    }
		
		
		
		
		
		//statistic
				//5dit mel service w badalt esm
		// http://localhost:8083/SpringMVC/transaction/transactionWeek
				@GetMapping("/transactionWeek")
			    public Map<String, Integer> TransactionWeek()
			    {
			        return transactionService.NbrTransactionWeek() ;
			    }
				
				
				
				
				@PostMapping("/withdraw/{account_id1}/{account_id2}")
				public String withdraw(@RequestParam("withdrawal_amount")String withdrawalAmount, @PathVariable("account_id1")Long accountID1, @PathVariable ("account_id2") Long accountID2, RedirectAttributes redirectAttributes) {
					String errorMessage;
					
					double currentBalance1 = accountRepository.getAccountBalance(accountID1);
					double currentBalance2 = accountRepository.getAccountBalance(accountID2);
					
					 Account account1 = new Account(accountID1) ;
					 Account account2 = new Account(accountID2) ;
					
					//CONVERT VARIABLES
					double withdrawal_amount= Double.parseDouble(withdrawalAmount);
					 // int account_id = Integer.parseInt(accountID);  hedhi w li fou9ha 7atithom string melawel bech najamt na3mel accountID.isEmpty();
					
					//CHECK FOR 0 VALUES
					if(withdrawal_amount== 0) {
						return "withdrawal amount cannot be 0, please enter a value greater than 0";	
						//redirectAttributes.addFlashAttribute("error", errorMessage);
						//return "redirect:/app/dashboard";
					}
					
					//CHECK IF TRANSFER AMOUNT IS MORE THAN CURRENT BALANCE:
			        if(currentBalance1 < withdrawal_amount){
			            return "You Have insufficient Funds to perform this Withdrawal!";
			            // Log Failed Transaction:
			            //transactionRepository.logTransaction(account_id, "Withdrawal", withdrawal_amount, "online", "failed", "Insufficient Funds", currentDateTime);
			            //redirectAttributes.addFlashAttribute("error", errorMessage);
			            //return "failed transaction";
			        }
					
					else {
						//SET NEW BALANCE
						
						double newBalance1 = currentBalance1 - withdrawal_amount;
						double newBalance2 = currentBalance2 + withdrawal_amount;
						
						//UPDATE ACCOUNT BALANCE:
						accountRepository.changeAccountBalanceById(newBalance1, accountID1);
						accountRepository.changeAccountBalanceById(newBalance2, accountID2);
						
					
					
					}
					return "withdrawal successful";
				
				}

}

