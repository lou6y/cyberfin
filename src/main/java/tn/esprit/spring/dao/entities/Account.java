package tn.esprit.spring.dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long idAccount;
	private Long Balance;
	@OneToOne(mappedBy="account")
	private User user;
	public Account(Long idAccount, Long balance, User user) {
		super();
		this.idAccount = idAccount;
		Balance = balance;
		this.user = user;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}
	public Long getBalance() {
		return Balance;
	}
	public void setBalance(Long balance) {
		Balance = balance;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}