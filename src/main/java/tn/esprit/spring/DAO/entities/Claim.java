package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;

//RECLAMATION LEL FAILED TRANSACTION/PAYMENT

@Entity
@Table( name = "Claim")
public class Claim implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long claim_id;
	
	@JsonBackReference
	@ManyToOne
	Transaction transaction;
	private Long account_id;
    private String transaction_type;
    private double amount;
    private String source;
    private String status;
    private String reason_code;
    private LocalDateTime created_at;
	//PAR DEFAUT
	@Value("${some.key:not treated}")
	private String state;
	

	public Claim() {
		super();
	}

	public Claim( Transaction transaction,Long claim_id, Long account_id, String transaction_type, double amount,
			String source, String status, String reason_code, LocalDateTime created_at, String state) {
		super();
		this.transaction = transaction;
		this.claim_id = claim_id;
		this.account_id = account_id;
		this.transaction_type = transaction_type;
		this.amount = amount;
		this.source = source;
		this.status = status;
		this.reason_code = reason_code;
		this.created_at = created_at;
		this.state = state;
		
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public Long getClaim_id() {
		return claim_id;
	}

	public void setClaim_id(Long claim_id) {
		this.claim_id = claim_id;
	}


	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason_code() {
		return reason_code;
	}

	public void setReason_code(String reason_code) {
		this.reason_code = reason_code;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	





	
	
	
	
}
