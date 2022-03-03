package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "Claim")
public class Claim implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idClaim")
	private Long idClaim;
	
	@Enumerated(EnumType.STRING)
	private TransactionType topic;
	
	@Column(name="description")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	

	public Claim() {
		super();
	}
	

	public Claim(Long idClaim, Status status, TransactionType topic, String description,
			Set<Transaction> transactions) {
		super();
		this.idClaim = idClaim;
		this.status = status;
		this.topic = topic;
		this.description = description;
		this.transactions = transactions;
	}

	

	public Long getIdClaim() {
		return idClaim;
	}


	public void setIdClaim(Long idClaim) {
		this.idClaim = idClaim;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public TransactionType getTopic() {
		return topic;
	}


	public void setTopic(TransactionType topic) {
		this.topic = topic;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}




	@ManyToMany(mappedBy="TransactionClaims")
	private Set<Transaction> transactions;
	
	
	
}
