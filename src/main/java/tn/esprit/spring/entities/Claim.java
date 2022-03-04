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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	



	public Claim(Long idClaim, TransactionType topic, String description, Status status, Transaction transaction) {
		super();
		this.idClaim = idClaim;
		this.topic = topic;
		this.description = description;
		this.status = status;
		this.transaction = transaction;
	}


	public Long getIdClaim() {
		return idClaim;
	}


	public void setIdClaim(Long idClaim) {
		this.idClaim = idClaim;
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


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Transaction getTransaction() {
		return transaction;
	}


	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}



	@JsonBackReference
	@ManyToOne
	Transaction transaction;
	
	
	
	
}
