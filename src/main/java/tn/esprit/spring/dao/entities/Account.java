package tn.esprit.spring.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  
  
}