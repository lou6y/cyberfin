package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "T_OFFER")
public class Offer implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	@Column(name="idOffer")
	private Long idOffer;
	
	@Column(name="type")
	private String type;
	
	@Column(name="description")
	private String description;
	
	@Column(name="rate")
	private Integer rate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="offer")
	private Set<Feedback> Feedbacks;
	
	@ManyToOne
	User user;
	
	@Override
	public String toString() {
		return "Offer [idOffer=" + idOffer + ", type=" + type + ", description=" + description + ", rate=" + rate + "]";
	}

	public Offer(Long idOffer, String type, String description, Integer rate) {
		super();
		this.idOffer = idOffer;
		this.type = type;
		this.description = description;
		this.rate = rate;
	}

	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(Long idOffer) {
		this.idOffer = idOffer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	
	
	
	
}
