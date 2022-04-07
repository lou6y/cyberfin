package tn.esprit.spring.DAO.entities;
import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Value;

@Entity
@Value
public class ClaimInfo implements Serializable{
	
	//class claim
	@Id
	LocalDateTime ccreated_at;
	
	//class account:balance nkharjou bel idAccount;
	Long aidAccount;
	double aBalance;
	
	//class transaction:count nb transaction 
	String ttypetransaction;
	
	//class user
	Long userId;


	public ClaimInfo(LocalDateTime ccreated_at, Long aidAccount, double aBalance, String ttypetransaction, Long userId) {
		super();
		this.ccreated_at = ccreated_at;
		this.aidAccount = aidAccount;
		this.aBalance = aBalance;
		this.ttypetransaction = ttypetransaction;
		this.userId=userId;
		
	}
	
	
	 

	

}
