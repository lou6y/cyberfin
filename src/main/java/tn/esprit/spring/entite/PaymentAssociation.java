package tn.esprit.spring.entite;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table( name= "PaymentAssociation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentAssociation implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long idPayment;
	
	private LocalDate paymentDate;

	private Boolean validPayment=false;
	private Boolean status;
	private Long client;
	@ManyToOne
	private Association associationP;
	
	

}
