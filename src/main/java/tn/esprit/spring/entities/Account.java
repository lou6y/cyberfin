package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="account")
	private Set<Loan> loans;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountinvest")
	private Set<Invest> Invests;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountassociation")
	private Set<Association> Associations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountransaction")
	private Set<Transaction> Transactions;
	
}