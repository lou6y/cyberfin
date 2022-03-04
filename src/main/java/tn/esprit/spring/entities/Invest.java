package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;



@Entity
@Table(name = "Invest")



public class Invest implements Serializable{
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
	
	@Column(name = "Interest_Rate")
    private double interest ;
	
	@Column(name = "Invest_Start_Date")
	private LocalDateTime Investstart;
	
	@Column(name = "Invest_End_Date")
	private LocalDateTime Investend;
	
	@Column(name = "Email")
	private String Email;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		Invest.serialVersionUID = serialVersionUID;
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

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	@ManyToOne
	Account accountinvest;
}
	



