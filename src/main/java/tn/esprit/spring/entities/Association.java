package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Id;

@Entity
@Table( name= "Association")
public class Association implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id_association")
	private long id_association;
	private float SumToPay;
	private float Sum;
	private String Description;
	
	@Temporal (TemporalType.DATE)
	
	private Date CreationDate;
	private int Places;
	private int NbMonths;
	@Temporal (TemporalType.DATE)
	private Date FinishDate;
	@Temporal (TemporalType.DATE)
	private Date PaymentDate;
	public long getId_association() {
		return id_association;
	}
	public void setId_association(long id_association) {
		this.id_association = id_association;
	}
	public float getSumToPay() {
		return SumToPay;
	}
	public void setSumToPay(float sumToPay) {
		SumToPay = sumToPay;
	}
	public float getSum() {
		return Sum;
	}
	public void setSum(float sum) {
		Sum = sum;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	public int getPlaces() {
		return Places;
	}
	public void setPlaces(int places) {
		Places = places;
	}
	public int getNbMonths() {
		return NbMonths;
	}
	public void setNbMonths(int nbMonths) {
		NbMonths = nbMonths;
	}
	public Date getFinishDate() {
		return FinishDate;
	}
	public void setFinishDate(Date finishDate) {
		FinishDate = finishDate;
	}
	public Date getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}
	public Association(long id_association, float sumToPay, float sum, String description, Date creationDate,
			int places, int nbMonths, Date finishDate, Date paymentDate) {
		super();
		this.id_association = id_association;
		SumToPay = sumToPay;
		Sum = sum;
		Description = description;
		CreationDate = creationDate;
		Places = places;
		NbMonths = nbMonths;
		FinishDate = finishDate;
		PaymentDate = paymentDate;
	}
	@Override
	public String toString() {
		return "Association [id_association=" + id_association + ", SumToPay=" + SumToPay + ", Sum=" + Sum
				+ ", Description=" + Description + ", CreationDate=" + CreationDate + ", Places=" + Places
				+ ", NbMonths=" + NbMonths + ", FinishDate=" + FinishDate + ", PaymentDate=" + PaymentDate + "]";
	}
	
	
	@ManyToOne
	Account accountassociation;
	
	
}
