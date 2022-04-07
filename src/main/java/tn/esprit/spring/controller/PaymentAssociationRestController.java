package tn.esprit.spring.controller;

import java.time.LocalDate;
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

import tn.esprit.spring.entite.Association;
import tn.esprit.spring.entite.PaymentAssociation;
import tn.esprit.spring.impl.PaymentAssociationServiceIMPL;


@RestController
@RequestMapping("/payement")
public class PaymentAssociationRestController {
	
@Autowired
PaymentAssociationServiceIMPL service;
	
	// http://localhost:8081/CyberFin/payement/retrieve-all-payments
			@GetMapping("/retrieve-all-payments")
			@ResponseBody
			public List<PaymentAssociation> getPayments() {
				List<PaymentAssociation> listassociation = service.retriveAllPayments();
				return listassociation;
				}

			
			// http://localhost:8081/CyberFin/payement/retrieve-payment/1
					@GetMapping("/retrieve-payments/{payment-id}")
					@ResponseBody
					public PaymentAssociation retrievePaymentId(@PathVariable("payment-id") Long id) {
					return service.retrievePaymentId(id);
					}
			
			
			
			
			// http://localhost:8081/CyberFin/payement/add-payment/1
					@PostMapping("/add-payment/{payment-id}")
					@ResponseBody
					public void ajoutPaymentAssociation(@PathVariable("payment-id") Long id)
					
					{
						 service.addPayment(id);
						
					
					}
		  
					
					
					// http://localhost:8081/CyberFin/payement/remove-payment/{payment-id}
					@DeleteMapping("/remove-payment/{payment-id}")
					@ResponseBody
					public void removePayment(@PathVariable("payment-id") Long id) {
						service.deletePayment(id);
					}
					
					//http://localhost:8081/CyberFin/payement/modify-payment
					@PutMapping("/modify-payment")
					@ResponseBody
					public PaymentAssociation modifyPaymentA(@RequestBody PaymentAssociation p) {
					return service.uploadPayment(p);
					}
					
					
	
					// http://localhost:8081/CyberFin/payement/retrieve-all-reservation/1
					@GetMapping("/retrieve-all-reservation/{association-id}")
					@ResponseBody
					public List<PaymentAssociation> getReservationP(@PathVariable("association-id") Long id) {
						List<PaymentAssociation> listreservation = service.getReservation(id);
						return listreservation;
						}
	
					
					// http://localhost:8081/CyberFin/payement/add-reservation/1/1/1
					@PostMapping("/add-reservation/{a-id}/{p-id}/{c-id}")
					@ResponseBody
					public void ajoutResrvation(@PathVariable("a-id") Long ida,@PathVariable("p-id") Long idp,@PathVariable("c-id") Long idc)
					
					{
						 service.MakeReservation(idp, ida, idc);
						
					
					}
					
					// http://localhost:8081/CyberFin/payement/pay/1
					@PostMapping("/pay/{payment-id}")
					@ResponseBody
					public void Payer(@PathVariable("payment-id") Long idp)
					
					{
						 service.Pay(idp);
						
					
					}
					
					
					// http://localhost:8081/CyberFin/payement/penality/1
					@PutMapping("/penality/{payement-id}")
					@ResponseBody
					public void Penaliser(@PathVariable("payement-id") Long idp)
					
					{
						 service.Penality( idp);
						
					
					}
					
					// http://localhost:8081/CyberFin/payement/harrab
					@GetMapping("/harrab")
					@ResponseBody
					public List< Association> retirieveNonPayers() {
						//LocalDate date=LocalDate.now();
						List<Association> listassociation = service.nonpayee();
					return listassociation;
					}
		  
					// http://localhost:8081/CyberFin/payement/suseptible
					@GetMapping("/suseptible")
					@ResponseBody
					public List< Long> getSuseptible() {
						
						List<Long> listIdAccount = service.suseptible();
					return listIdAccount;
					}
	
}
