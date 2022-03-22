package tn.esprit.spring.entite;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table( name= "Association")
public class Association implements Serializable {
	
	@Id
	
	private long id_association;
	@Temporal (TemporalType.DATE)
	private Date creationDate;
	private String description;
	@Temporal (TemporalType.DATE)
	private Date finishDate;
	private int nbMonths;
	@Temporal (TemporalType.DATE)
	private Date paymentDate;
	private int places;
	
	private float sum;
	private float sumToPay;
	

	
	public long getId_association() {
		return id_association;
	}


	public void setId_association(long id_association) {
		this.id_association = id_association;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getFinishDate() {
		return finishDate;
	}


	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}


	public int getNbMonths() {
		return nbMonths;
	}


	public void setNbMonths(int nbMonths) {
		this.nbMonths = nbMonths;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public int getPlaces() {
		return places;
	}


	public void setPlaces(int places) {
		this.places = places;
	}


	public float getSum() {
		return sum;
	}


	public void setSum(float sum) {
		this.sum = sum;
	}


	public float getSumToPay() {
		return sumToPay;
	}


	public void setSumToPay(float sumToPay) {
		this.sumToPay = sumToPay;
	}


	public Association ()
	{
		super();
	}
	
	
	
	public Association(long id_association, Date creationDate, String description, Date finishDate, int nbMonths,
			Date paymentDate, int places, float sum, float sumToPay) {
		super();
		this.id_association = id_association;
		this.creationDate = creationDate;
		this.description = description;
		this.finishDate = finishDate;
		this.nbMonths = nbMonths;
		this.paymentDate = paymentDate;
		this.places = places;
		this.sum = sum;
		this.sumToPay = sumToPay;
	}


	@Override
	public String toString() {
		return "Association [id_association=" + id_association + ", creationDate=" + creationDate + ", description="
				+ description + ", finishDate=" + finishDate + ", nbMonths=" + nbMonths + ", paymentDate=" + paymentDate
				+ ", places=" + places + ", sum=" + sum + ", sumToPay=" + sumToPay + "]";
	}


	
	
	

}
