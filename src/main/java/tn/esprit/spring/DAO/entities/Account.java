package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Account implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="idAccount")
	private Long idAccount;
	private double Balance;
	@OneToOne(mappedBy="account")
	private User user;
	
	public Account(Long idAccount, double balance, User user) {
		super();
		this.idAccount = idAccount;
		this.Balance = balance;
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
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
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
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountransaction")
//	private Set<Transaction> TransactionsAccounts;
	
	
	@OneToMany(targetEntity= Transaction.class, cascade=CascadeType.ALL)
	//el account id bech ywali foreign key
	@JoinColumn(name="account_id", referencedColumnName="idAccount")  //KENET at_fk
	private List<Transaction> transactions;
	
	
	
	
}