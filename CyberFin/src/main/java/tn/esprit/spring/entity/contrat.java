package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Contrat")



public class contrat implements Serializable{
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "User_ID")
	private long userId;
	
	@Column(name = "Amount")
	private int amount;

	@Column(name = "Invest_Start_Date")
	private LocalDateTime Investstart;
	
	@Column(name = "Invest_End_Date")
	private LocalDateTime Investend;
	
	@Column(name = "digital_identity")
	private String digital_identity;
	
	@Column(name = "E_signature")
	private String E_signature;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		contrat.serialVersionUID = serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getInveststart() {
		return Investstart;
	}

	public void setInveststart(LocalDateTime investstart) {
		Investstart = investstart;
	}

	public LocalDateTime getInvestend() {
		return Investend;
	}

	public void setInvestend(LocalDateTime investend) {
		Investend = investend;
	}

	public String getDigital_identity() {
		return digital_identity;
	}

	public void setDigital_identity(String digital_identity) {
		this.digital_identity = digital_identity;
	}

	public String getE_signature() {
		return E_signature;
	}

	public void setE_signature(String e_signature) {
		E_signature = e_signature;
	}
	
}
