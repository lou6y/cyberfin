package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;

@Entity
@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table( name = "T_OFFER")
public class Offer implements Serializable {

	
	@Id
	@Column(name="idOffer")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@NonNull private Long idOffer;
	
	
	@Enumerated(EnumType.STRING)
	//@Column(name="type")
	private TypeOffer typeOffer ;
	
	@Column(precision=2, scale=2, columnDefinition = "double default 0" )
	//@Type(type = "big_decimal") 
    //@Value("0.0")
	//@Column(precision=10, scale=2)
	private Double   ratingAvg ;
	
	
	@Column(name="description")
	@NonNull 
	private String description;

	
	@Temporal(TemporalType.DATE)
	private Date datedebut;
	@Temporal(TemporalType.DATE)
	private Date datefin;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="offer")
	@NonNull private Set<Feedback> Feedbacks;
	
	@JsonIgnore
	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private Set<Rating> ratings;
	
	@Column(columnDefinition = "integer default 0")
    private Integer countUser;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	// @JsonIgnore
	private Set<User> users;
	
	
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
	this.users = users;
	}


}