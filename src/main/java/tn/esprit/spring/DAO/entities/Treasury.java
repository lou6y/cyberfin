package tn.esprit.spring.DAO.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "Treasury")
public class Treasury implements Serializable{
	
	@Id
	private double treasuryamount;

	public Treasury() {
		super();
	}

	public Treasury(double treasuryamount) {
		super();
		this.treasuryamount = treasuryamount;
	}

	public double getTreasuryamount() {
		return treasuryamount;
	}

	public void setTreasuryamount(double treasuryamount) {
		this.treasuryamount = treasuryamount;
	}
	
	

}
