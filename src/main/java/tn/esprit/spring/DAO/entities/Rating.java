package tn.esprit.spring.DAO.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Rating implements Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull private Long idRating;
	
	

	private float  value;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    private Offer offer;
	
	
	
}
