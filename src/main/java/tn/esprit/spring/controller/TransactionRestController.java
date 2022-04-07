package tn.esprit.spring.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.DAO.entities.Account;
import tn.esprit.spring.DAO.entities.ClaimInfo;
import tn.esprit.spring.DAO.entities.PaymentHistory;
import tn.esprit.spring.DAO.entities.Transaction;
import tn.esprit.spring.DAO.entities.TransactionHistory;
import tn.esprit.spring.DAO.entities.User;
import tn.esprit.spring.services.Interfaces.ClaimService;
import tn.esprit.spring.services.Interfaces.TransactionService;
import tn.esprit.spring.services.Interfaces.TreasuryService;
import tn.esprit.spring.DAO.repositories.TransactionRepository;
import tn.esprit.spring.DAO.repositories.AccountRepository;
import tn.esprit.spring.DAO.repositories.ClaimRepository;
import tn.esprit.spring.DAO.repositories.PaymentHistoryRepository;
import tn.esprit.spring.DAO.repositories.PaymentRepository;
import tn.esprit.spring.DAO.repositories.TransactHistoryRepository;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
	
	
//AUTOWIRED kol manji nesta3mel service
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	ClaimService claimService;
	
	@Autowired 
	TransactionRepository transactionRepository;
	
	@Autowired 
	PaymentRepository paymentRepository;
	
	@Autowired 
	TreasuryService treasuryService;
	
	@Autowired 
	AccountRepository accountRepository;
	
	@Autowired 
	ClaimRepository claimRepository;
	
	@Value("${jobs.enabled:false}")
	  private boolean isEnabled;
	
	
	double currentBalance1;
    double newBalance1;
    double treasuryamount;
    LocalDateTime currentDateTime = LocalDateTime.now();
    
  /*  
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    private TransactHistoryRepository transactHistoryRepository;
  */ 

    User user;
    
    
	
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
		
		
/*
		//TEST INSERT (NON OBLI)
		@ApiOperation(value = "add transaction")
				@PostMapping
				 @ResponseBody
				 void historiqueTransact(@RequestParam("account_id")Long account_id,
						 			@RequestParam("transact_type")String transact_type,
						 			@RequestParam("amount")double amount,
						 			@RequestParam("source")String source ) {
					 
					 String reasonCode = "test transact!";
				        transactionService.historiqueTransact(account_id, transact_type, amount, source, "success", reasonCode, currentDateTime);

				 }  */
				
				
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
				int taxperdeposit=10;
				//get current balance
					currentBalance1 = transactionRepository.getAccountBalance(accountID);
				//CHECK FOR 0 VALUES
					if(depositAmount == 0) {
						return "withdrawal amount cannot be 0, please enter a value greater than 0";	
					}
					
					//SET NEW BALANCE nekhou alih 10dt
					 newBalance1 = currentBalance1 + depositAmount -taxperdeposit;
					
					//UPDATE ACCOUNT BALANCE:
					transactionRepository.changeAccountBalanceById(newBalance1, accountID);
			        transactionService.historiqueTransact(accountID, "deposit", depositAmount, "online", "success", "Deposit Transaction Successful",currentDateTime);

			        //if nb deposit>5 ma3atech nekhou alih tax
			        int nbtransactionsbytype= transactionRepository.getnumbertransactionsbytypeandstatus(accountID,"deposit", "success");
			        if(nbtransactionsbytype>=5) {
			        	double newBalanceifdeposit5= newBalance1+taxperdeposit;
			        	transactionRepository.changeAccountBalanceById(newBalanceifdeposit5, accountID);
			        }
			        	
					
					return "deposit successful";
				}
				
				
				//YAB3ATH FLOUS
				@PostMapping("/transfer")
			    public String transfer(@RequestParam("transfer_from") String transfer_from,
			                           @RequestParam("transfer_to") String transfer_to,
			                           @RequestParam("transfer_amount")String transfer_amount){
					int taxpertransfer=5;
					// Init Error Message Value:
			        String errorMessage;

			        // CHECK FOR EMPTY FIELDS: felfront
			        //if(transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty()){
			        //     return "The account transferring from and to along with the amount cannot be empty!";
			           
			       // }

			        // CONVERT VARIABLES:
			        Long transferFromId = Long.parseLong(transfer_from);
			        Long transferToId = Long.parseLong(transfer_to);
			        double transferAmount = Double.parseDouble(transfer_amount);

			        // CHECK IF TRANSFERRING INTO THE SAME ACCOUNT:
			        if(transferFromId == transferToId){
			            return "Cannot Transfer Into The same Account, Please select the appropriate account to perform transfer";
			            
			        }

			        //CHECK FOR 0 (ZERO) VALUES:
			        if(transferAmount == 0){
			            return errorMessage = "Cannot Transfer an amount of 0 , please enter a value greater than 0";
			            
			        }
			        //  GET CURRENT BALANCE:
			        double currentBalanceOfAccountTransferringFrom  = transactionRepository.getAccountBalance(transferFromId);

			        //CHECK IF TRANSFER AMOUNT IS MORE THAN CURRENT BALANCE:
			        if(currentBalanceOfAccountTransferringFrom < transferAmount){
			         
			            //Failed Transaction:
			            transactionService.historiqueTransact(transferFromId, "Transfer", transferAmount, "online", "failed", "Insufficient Fund", currentDateTime);
			            
			          //SINON MECH BECH YA3REF RECLAMATION 3ALA ENA TRANSACTION TJI (LEZEM NA3REF L ID MTE3HA EKA ALECH 3MALT gettransctionid by currentdatetime(w mech account_id sinon yraja3li barcha results)
				          Long transaction_transaction_id=claimService.findTopByOrderByTransactIdDesc(currentDateTime);
				          
				        //tjini reclamation automatique
				          claimService.failedTransact(transaction_transaction_id,transferFromId ,"Transfer",transferAmount, "online","failed", "Insufficient Funds", currentDateTime, "not treated");  
				          
			            return "You Have insufficient Funds to perform this Transfer!";
			        }
			        
			      //if nb claim fel transfer>5 ma3atech nekhou alih tax// AVEC JOINTURE 
			        
			        double  currentBalanceOfAccountTransferringTo = transactionRepository.getAccountBalance(transferToId);

			        // SET NEW BALANCE://NZID NEKHOU 3AL TRANSFERINGFROM TAX
			        double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount -taxpertransfer;

			        double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

			        // Changed The Balance Of the Account Transferring From:
			        transactionRepository.changeAccountBalanceById( newBalanceOfAccountTransferringFrom, transferFromId);

			        // Changed The Balance Of the Account Transferring To:
			        transactionRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

			        // if Successful Transaction: n3amer f table transaction
			        transactionService.historiqueTransact(transferFromId, "Transfer", transferAmount, "online", "success", "Transfer Transaction Successful",currentDateTime);

			        // if numberclaims by type transfer>2 nsem7ou fel tax li na7ithoulou
			        int numberclaimsbytype= claimRepository.getnumberclaimsbytype(transferFromId,"Transfer");
			        if(numberclaimsbytype>=2) {
			        	double newBalanceifTransfer2= newBalanceOfAccountTransferringFrom+taxpertransfer;
			        	transactionRepository.changeAccountBalanceById(newBalanceifTransfer2, transferFromId);
			        }

			        
			        
			        //if balance=0 nzidou 10 fel compte w ndeclanchi el @scheduled
			        if (newBalanceOfAccountTransferringFrom ==0) {
			        	isEnabled=true;
						
						double bl= newBalanceOfAccountTransferringFrom +=10;
						transactionRepository.changeAccountBalanceById( bl, transferFromId);
						//fixedDelayMethod();   //KHEDMETT
						do {
						double oldtreasuryamount=treasuryService.getTreasury(treasuryamount);
						double currenttreasuryamount= oldtreasuryamount-10;
						treasuryService.changeTreasury(currenttreasuryamount);
						}while (newBalanceOfAccountTransferringFrom ==0 );
						return "added 10";
					}
			         
			        return "Amount Transferred Successfully!";
			       
			    }
				
				
				//MARA kol ras 3am nzidhom flous w nahi mel treasury ken nb transaction li 3malhom >=5
				@Scheduled(cron = " 0 0 0 1 1 * ")
				public void fixedDelayMethod() {
					
				//	if (isEnabled)
					//	System.out.println("just withdrawed 10 DT from the Treasury");
					//int nbtrperaccount=0;
					List<Transaction> ListTransactions = (List<Transaction>)(transactionRepository.findAll());
					
					for(Transaction tr : ListTransactions)	
					{	
						Long id= tr.getAccount_id();
						List <Transaction> ListTr = (List<Transaction>)(transactionService.retrieveTransactionByAccid(id));
						
						long nbtrperaccount = ListTr .stream().count();
						//si nb transactions li 3malhom >=5 nzidou fel balance 10dt
						if(nbtrperaccount>= 5)
						{ 
							Account Listaccount = accountRepository.findById(id).get();
							//getIdAccount()  setBalance()
								double bal= Listaccount.getBalance();
								double newbal=bal+10;
								Listaccount.setBalance(newbal);
							accountRepository.save(Listaccount);
							
						transactionRepository.save(tr);
						double oldtreasuryamount=treasuryService.getTreasury(treasuryamount);
						double newtreasuryamount=oldtreasuryamount-10;
						treasuryService.changeTreasury(newtreasuryamount);
						
						}
					
				}}
				
				//teb3a l @scheduled
				//nthabet 9adech 3andi men transaction per id bech nkhadem el scheduling
				@GetMapping("/retrieve-transactionbyid/{account_id}")
				@ResponseBody
				public List<Transaction> retrieveTransactionByAccid(@PathVariable("account_id") Long account_id){
					return transactionService.retrieveTransactionByAccid(account_id);
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
						return "Withdrawal Amount Cannot be of 0 (Zero) value, please enter a value greater than 0 (Zero)";
			          
					}
					
					//CHECK IF TRANSFER AMOUNT IS MORE THAN CURRENT BALANCE:
			        if(currentBalance1 < withdrawal_amount){
			        	String errorMessage = "You Have insufficient Funds to perform this Withdrawal!";
			            // Log Failed Transaction:
			            transactionService.historiqueTransact(accountID1, "Withdrawal", withdrawal_amount, "online", "failed", "Insufficient Funds", currentDateTime);
			            
			            
			            Long transaction_transaction_id=claimService.findTopByOrderByTransactIdDesc(currentDateTime);
				          
				        //tjini reclamation automatique
				          claimService.failedTransact(transaction_transaction_id,accountID1 ,"Withdrawal",withdrawal_amount, "online","failed", "Insufficient Funds", currentDateTime, "not treated");
			            
			            return "You Have insufficient Funds to perform this Withdrawal!";
			        }
					
					else {
						//SET NEW BALANCE
						 newBalance1 = currentBalance1 - withdrawal_amount;
						
						//UPDATE ACCOUNT BALANCE:
						transactionRepository.changeAccountBalanceById(newBalance1, accountID1);
				        transactionService.historiqueTransact(accountID1, "Withdrawal", withdrawal_amount, "online", "success", "Withdrawal Transaction Successful",currentDateTime);

					
					}
					return "withdrawal successful";
				
				}
				
				
				
				//PAYMENT // EL ACCOUNT_ID HOUWA COMPTE TE3I LI BECH ENA7I MENNOU FLOUS
				//li fel param houwa libech n3amrou fel swagger
				@PostMapping("/payment")
			    public String payment(@RequestParam("beneficiary")String beneficiary,
			                          @RequestParam("account_number")String account_number,
			                          @RequestParam("account_id")String account_id,
			                          @RequestParam("reference")String reference,
			                          @RequestParam("payment_amount")String payment_amount){
//TRAITEMENT AUTOMATIQUE reclamation tetreata automatiquement
					//select from claim claim.transaction.account.user  (nchouf reclamation teb3a ena user) FEL JOITURE
					//el scheduled ta3mel actualisation auto lel database (najem na3mala  tbadel claim to treated)
					//stat 3ala 9adech men rec fel 3am
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
			            //kenfailed payment kifkif tet3amer f table payment
			            paymentRepository.makePayment(accountID, beneficiary, account_number, paymentAmount, reference, "failed", reasonCode, currentDateTime);
			            //if Failed Transaction:
			            //tet3amer f table transaction
			          transactionService.historiqueTransact(accountID, "Payment", paymentAmount, "online", "failed", "Insufficient Funds", currentDateTime);
			          
			          //SINON MECH BECH YA3REF RECLAMATION 3ALA ENA TRANSACTION TJI (LEZEM NA3REF L ID MTE3HA EKA ALECH 3MALT gettransctionit by currentdatetime
			        //bech najamt nrecuperi el transaction_id mta3 el claim fel transactionrestcontroller
			     	 //findtransactionidbycreattime= findTopByOrderByTransactIdDesc(currentDateTime)
			          Long transaction_transaction_id=claimService.findTopByOrderByTransactIdDesc(currentDateTime);
			          
			        //tjini reclamation automatique
			          claimService.failedTransact(transaction_transaction_id,accountID,"Payment",paymentAmount, "online","failed", "Insufficient Funds", currentDateTime, "not treated");  
			         			            			     
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
			        transactionService.historiqueTransact(accountID, "Payment", paymentAmount, "online", "success", "Payment Transaction Successful",currentDateTime);

			        successMessage = reasonCode;
			      
			        return "Payment Processed Successfully!";
			    }
				
		
				
				
				
				
				
				
				
			
				
				
				
				
				//BECH N3ADI FEL PARA ID USER W HOUWA YA3TINI HISTORIQUE MTE3OU
				
			/*  NORMALEMENT LEL FRONT HEDHI . BECH KI NCO 3ALA USER YATLA3LI HISTORIQUE DES TRANSACTIONS MTE3OU	
				@GetMapping("/payment_history")
			    public ModelAndView getPaymentHistory(HttpSession session){
			        // Set View:
			        ModelAndView getPaymentHistoryPage = new ModelAndView("paymentHistory");

			        // Get Logged In User:\
			        user = (User) session.getAttribute("user");

			        // Get Payment History byt id(kol user nkharajlou l paymrnt history mte3Ou)/ Records:
			        List<PaymentHistory> userPaymentHistory = paymentHistoryRepository.getPaymentRecordsById(user.getId());

			        getPaymentHistoryPage.addObject("payment_history", userPaymentHistory);

			        return getPaymentHistoryPage;

			    }


			    @GetMapping("/transact_history")
			    public ModelAndView getTransactHistory(HttpSession session){
			        // Set View:
			        ModelAndView getTransactHistoryPage = new ModelAndView("transactHistory");

			        // Get Logged In User:\
			        user = (User) session.getAttribute("user");

			        // Get Payment History / Records:
			        List<TransactionHistory> userTransactHistory = transactHistoryRepository.getTransactionRecordsById(user.getId());

			        getTransactHistoryPage.addObject("transact_history", userTransactHistory);

			        return getTransactHistoryPage;

			    } */
				
				

}

