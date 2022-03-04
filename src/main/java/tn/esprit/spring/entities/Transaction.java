package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table( name = "Transaction")
public class Transaction implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idTransaction")
	private Long idTransaction;
	
	@Column(name="dateTransaction")
	@Temporal (TemporalType.DATE)
	private Date dateTransaction;
	
	@Column(name="sumToTransfer")
	private int sumToTransfer;
	
	@Column(name="totalSum")
	private int totalSum;
	
	@Enumerated(EnumType.STRING)
	private TransactionType  transactiontype;

	public Transaction() {
		super();
		
	}

	 
	public Transaction(Long idTransaction, Date dateTransaction, int sumToTransfer, int totalSum,
			TransactionType transactiontype, Set<Claim> claim) {
		super();
		this.idTransaction = idTransaction;
		this.dateTransaction = dateTransaction;
		this.sumToTransfer = sumToTransfer;
		this.totalSum = totalSum;
		this.transactiontype = transactiontype;
		this.claim = claim;
	}
	

	public Long getIdTransaction() {
		return idTransaction;
	}


	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}


	public Date getDateTransaction() {
		return dateTransaction;
	}


	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}


	public int getSumToTransfer() {
		return sumToTransfer;
	}


	public void setSumToTransfer(int sumToTransfer) {
		this.sumToTransfer = sumToTransfer;
	}


	public int getTotalSum() {
		return totalSum;
	}


	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}


	public TransactionType getTransactiontype() {
		return transactiontype;
	}


	public void setTransactiontype(TransactionType transactiontype) {
		this.transactiontype = transactiontype;
	}


	public Set<Claim> getClaim() {
		return claim;
	}


	public void setClaim(Set<Claim> claim) {
		this.claim = claim;
	}


	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="transaction")
	private Set<Claim> claim;
	
	@ManyToOne
	Account accountransaction;
	
	
}
