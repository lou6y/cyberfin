package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table( name = "T_FEEDBACK")
public class Feedback implements Serializable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)

	
	@Column(name="idFeedback")
	@NonNull private Long idFeedback;
	
	@Column(name="descriptionFeedback")
	private String descriptionFeedback;
	
	@Column(name="rateFeedback")
	private Integer rateFeedback;
	
	
	@ManyToOne
	Offer offer;
	
/*
	@Override
	public String toString() {
		return "Feedback [idFeedback=" + idFeedback + ", descriptionFeedback=" + descriptionFeedback + ", rateFeedback="
				+ rateFeedback + "]";
	}
    
	


	public Long getIdFeedback() {
		return idFeedback;
	}




	public void setIdFeedback(Long idFeedback) {
		this.idFeedback = idFeedback;
	}




	public String getDescriptionFeedback() {
		return descriptionFeedback;
	}




	public void setDescriptionFeedback(String descriptionFeedback) {
		this.descriptionFeedback = descriptionFeedback;
	}




	public Integer getRateFeedback() {
		return rateFeedback;
	}




	public void setRateFeedback(Integer rateFeedback) {
		this.rateFeedback = rateFeedback;
	}




	public Feedback(Long idFeedback, String descriptionFeedback, Integer rateFeedback) {
		super();
		this.idFeedback = idFeedback;
		this.descriptionFeedback = descriptionFeedback;
		this.rateFeedback = rateFeedback;
	}



	public Feedback() {
		super();
		
	}
	*/

}
