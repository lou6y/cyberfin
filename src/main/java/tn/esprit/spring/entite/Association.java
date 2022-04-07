package tn.esprit.spring.entite;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table( name= "Association")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Association implements Serializable {
	
	@Id
	
	private long id_association;
	//@Temporal (TemporalType.DATE)
	private LocalDate creationDate;
	private String description;
	//@Temporal (TemporalType.DATE)
	private LocalDate finishDate;
	private int nbMonths;
	@Temporal (TemporalType.DATE)
	private Date paymentDate;
	private int places;
	private float sum;
	private float sumToPay;
	private float intrests;
	private boolean validPayment=false;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy="association")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Account> participants;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<PaymentAssociation> payment;
	
	

	@Override
	public String toString() {
		return "Association [id_association=" + id_association + ", creationDate=" + creationDate + ", description="
				+ description + ", finishDate=" + finishDate + ", nbMonths=" + nbMonths + ", paymentDate=" + paymentDate
				+ ", places=" + places + ", sum=" + sum + ", sumToPay=" + sumToPay + ", intrests=" + intrests
				+ ", validPayment=" + validPayment + ", Participants=" + participants + "]";
	}




	
	
	
	
	
	

}
