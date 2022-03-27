package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.services.Interfaces.TransactionService;
import tn.esprit.spring.DAO.repositories.TransactionRepository;
import tn.esprit.spring.DAO.repositories.PaymentRepository;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
	
	
//AUTOWIRED kol manji nesta3mel service
	@Autowired
	TransactionService transactionService;
	
	@Autowired 
	TransactionRepository transactionRepository;
	
	@Autowired 
	PaymentRepository paymentRepository;

	
	double currentBalance1;
    double newBalance1;
    LocalDateTime currentDateTime = LocalDateTime.now();
	
	// http://localhost:8083/SpringMVC/transaction/retrieve-all-transactions
	@ApiOperation(value = "afficher transactions")
	@GetMapping("/retrieve-all-transactions")
	@ResponseBody
	public List<Transaction> getTransactions() {
		List<Transaction> listTransactions = transactionService.retrieveAllTransactions();
		return listTransactions;
		}
	
	// http://localhost:8083/SpringMVC/transaction/retrieve-transaction/1
		@GetMapping("/retrieve-transaction/{transaction_id}")
		@ResponseBody
		public Transaction retrieveTransaction(@PathVariable("transaction_id") Long transaction_id) {
		return transactionService.retrieveTransaction(transaction_id);
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
		@DeleteMapping("/remove-transaction/{transaction_id}")
		@ResponseBody
		public void removeTransaction(@PathVariable("transaction_id") Long transaction_id) {
		transactionService.deleteTransaction(transaction_id);
		}
		
		
		//SELECT  TEKHDEM
		@GetMapping("/selecttransactionJPQL/{transaction_type}")
		@ResponseBody
		public List<Transaction> selectTransactionByTransactType(@PathVariable String transaction_type)
	    {
	        return transactionService.retrieveTransactionByTransactType(transaction_type) ;
	    }
		
		

		//INSERT
				@PostMapping
				 @ResponseBody
				 void makeTransact(@RequestParam("account_id")Long account_id,
						 			@RequestParam("transact_type")String transact_type,
						 			@RequestParam("amount")double amount,
						 			@RequestParam("source")String source ) {
					 
					 String reasonCode = "test transact!";
				        transactionService.makeTransact(account_id, transact_type, amount, source, "success", reasonCode, currentDateTime);

				 } 
				
				
		//DELETE  //modify zeda @transactional
		//ZEDet @TRANSACTIONAL fel REPOSITORY BECH NAJAMT 5ADAMT EL UPDATE wel DELETE sinon y9oli Spring JPA - javax.persistence.TransactionRequiredException: Executing an update/delete query
		//@Transactional
				@DeleteMapping("/deletetransactionJPQL/{transactiontype}")
				@ResponseBody
				public void delTransactionByTransactType(@PathVariable String transactiontype)
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
				
				
				
				
				
				///YSOB FLOUS
				@PostMapping("/deposit/{account_id}")
				public String deposit (@RequestParam("deposit_amount")double depositAmount, @PathVariable("account_id")Long accountID) {
				
				//get current balance
					currentBalance1 = transactionRepository.getAccountBalance(accountID);
				//CHECK FOR 0 VALUES
					if(depositAmount == 0) {
						return "withdrawal amount cannot be 0, please enter a value greater than 0";	
					}
					
					//SET NEW BALANCE
					 newBalance1 = currentBalance1 + depositAmount;
					
					//UPDATE ACCOUNT BALANCE:
					transactionRepository.changeAccountBalanceById(newBalance1, accountID);
					
					return "deposit successful";
				}
				
				
				//YAB3ATH FLOUS
				@PostMapping("/transfer")
			    public String transfer(@RequestParam("transfer_from") String transfer_from,
			                           @RequestParam("transfer_to") String transfer_to,
			                           @RequestParam("transfer_amount")String transfer_amount){
			        // Init Error Message Value:
			        String errorMessage;

			        // TODO: CHECK FOR EMPTY FIELDS:
			        //if(transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty()){
			        //     return "The account transferring from and to along with the amount cannot be empty!";
			           
			       // }

			        // TODO: CONVERT VARIABLES:
			        Long transferFromId = Long.parseLong(transfer_from);
			        Long transferToId = Long.parseLong(transfer_to);
			        double transferAmount = Double.parseDouble(transfer_amount);

			        // TODO: CHECK IF TRANSFERRING INTO THE SAME ACCOUNT:
			        if(transferFromId == transferToId){
			            return "Cannot Transfer Into The same Account, Please select the appropriate account to perform transfer";
			            
			        }

			        // TODO: CHECK FOR 0 (ZERO) VALUES:
			        if(transferAmount == 0){
			            return errorMessage = "Cannot Transfer an amount of 0 , please enter a value greater than 0";
			            
			        }

			     
			        // TODO: GET CURRENT BALANCE:
			        double currentBalanceOfAccountTransferringFrom  = transactionRepository.getAccountBalance(transferFromId);

			        // TODO: CHECK IF TRANSFER AMOUNT IS MORE THAN CURRENT BALANCE:
			        if(currentBalanceOfAccountTransferringFrom < transferAmount){
			         
			            // Log Failed Transaction:
			            //transactionRepository.logTransaction(transferFromId, "Transfer", transferAmount, "online", "failed", "Insufficient Funds", currentDateTime);
			            //return "You Have insufficient Funds to perform this Transfer!";
			        }

			        double  currentBalanceOfAccountTransferringTo = transactionRepository.getAccountBalance(transferToId);

			        // TODO: SET NEW BALANCE:
			        double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount;

			        double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

			        // Changed The Balance Of the Account Transferring From:
			        transactionRepository.changeAccountBalanceById( newBalanceOfAccountTransferringFrom, transferFromId);

			        // Changed The Balance Of the Account Transferring To:
			        transactionRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

			        // Log Successful Transaction:
			        //transactionRepository.logTransaction(transferFromId, "Transfer", transferAmount, "online", "success", "Transfer Transaction Successful",currentDateTime);

			        return "Amount Transferred Successfully!";
			       
			    }
				
				
				
				//YEJBED FLOUS
				@PostMapping("/withdraw/{account_id1}")
				public String withdraw(@RequestParam("withdrawal_amount")String withdrawalAmount, @PathVariable("account_id1")Long accountID1) {
					
					//get currentbalance
					currentBalance1 = transactionRepository.getAccountBalance(accountID1);
//					 Account account1 = new Account(accountID1) ;
					
					//CONVERT VARIABLES
					double withdrawal_amount= Double.parseDouble(withdrawalAmount);
					 // int account_id = Integer.parseInt(accountID);  hedhi w li fou9ha 7atithom string melawel bech najamt na3mel accountID.isEmpty();
					
					//CHECK FOR 0 VALUES
					if(withdrawal_amount== 0) {
						return "withdrawal amount cannot be 0, please enter a value greater than 0";	
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
						 newBalance1 = currentBalance1 - withdrawal_amount;
						
						//UPDATE ACCOUNT BALANCE:
						transactionRepository.changeAccountBalanceById(newBalance1, accountID1);
					
					}
					return "withdrawal successful";
				
				}
				
				
				
				//PAYMENT
				@PostMapping("/payment")
			    public String payment(@RequestParam("beneficiary")String beneficiary,
			                          @RequestParam("account_number")String account_number,
			                          @RequestParam("account_id")String account_id,
			                          @RequestParam("reference")String reference,
			                          @RequestParam("payment_amount")String payment_amount){

			        String errorMessage;
			        String successMessage;

			        // TODO: CONVERT VARIABLES:
			        //3MALTHOM STRING MELAWEL KHATER BA3D BECH NEST7A9HOM FEL CONTROL DE SAISIE FEL FRONT
			        Long accountID = Long.parseLong(account_id);
			        double paymentAmount = Double.parseDouble(payment_amount);

			        // TODO: CHECK FOR 0 (ZERO) VALUES:
			        if(paymentAmount == 0){
			            return "Payment Amount Cannot be of 0 value, please enter a value greater than 0 ";
			        }

			        // TODO: GET CURRENT BALANCE:
			        currentBalance1 = transactionRepository.getAccountBalance(accountID);

			        // TODO: CHECK IF PAYMENT AMOUNT IS MORE THAN CURRENT BALANCE:  CONTROLE DE SAISIE
			        if(currentBalance1 < paymentAmount){
			            errorMessage = "You Have insufficient Funds to perform this payment";
			            String reasonCode = "Could not Processed Payment due to insufficient funds!";
			            paymentRepository.makePayment(accountID, beneficiary, account_number, paymentAmount, reference, "failed", reasonCode, currentDateTime);
			            // Log Failed Transaction:
			          //  transactionRepository.logTransaction(accountID, "Payment", paymentAmount, "online", "failed", "Insufficient Funds", currentDateTime);
			            
			            return "You Have insufficient Funds to perform this payment";
			        }

			        // TODO SET NEW BALANCE FOR ACCOUNT PAYING FROM:
			        newBalance1 = currentBalance1 - paymentAmount;

			     // TODO: MAKE PAYMENT:
			        String reasonCode = "Payment Processed Successfully!";
			        paymentRepository.makePayment(accountID, beneficiary, account_number, paymentAmount, reference, "success", reasonCode, currentDateTime);
			        // TODO: UPDATE ACCOUNT PAYING FROM:
			        transactionRepository.changeAccountBalanceById(newBalance1, accountID);

			        // Log Successful Transaction:
			        //transactionRepository.logTransaction(accountID, "Payment", paymentAmount, "online", "success", "Payment Transaction Successful",currentDateTime);

			        successMessage = reasonCode;
			      
			        return "Payment Processed Successfully!";
			    }
				

}

