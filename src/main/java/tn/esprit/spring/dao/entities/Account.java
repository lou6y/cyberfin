package tn.esprit.spring.dao.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long balance;
  
  @OneToOne(mappedBy="account")
	private User user;
  @OneToMany(cascade = CascadeType.ALL, mappedBy="account")
	private Set<Loan> loans;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountinvest")
	private Set<Invest> Invests;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountassociation")
	private Set<Association> Associations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="accountransaction")
	private Set<Transaction> TransactionsAccounts;

  public Account() {
  }

public Account(Long balance, User user) {
	super();
	this.balance = balance;
	this.user = user;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getBalance() {
	return balance;
}

public void setBalance(Long balance) {
	this.balance = balance;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Set<Loan> getLoans() {
	return loans;
}

public void setLoans(Set<Loan> loans) {
	this.loans = loans;
}

public Set<Invest> getInvests() {
	return Invests;
}

public void setInvests(Set<Invest> invests) {
	Invests = invests;
}

public Set<Association> getAssociations() {
	return Associations;
}

public void setAssociations(Set<Association> associations) {
	Associations = associations;
}

public Set<Transaction> getTransactionsAccounts() {
	return TransactionsAccounts;
}

public void setTransactionsAccounts(Set<Transaction> transactionsAccounts) {
	TransactionsAccounts = transactionsAccounts;
}
  
  
}