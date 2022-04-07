package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity

public class React implements Serializable {
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		React.serialVersionUID = serialVersionUID;
	}

	public int getReactId() {
		return reactId;
	}

	public void setReactId(int reactId) {
		this.reactId = reactId;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public Post getPostLike() {
		return PostLike;
	}

	public void setPostLike(Post postLike) {
		PostLike = postLike;
	}

	public Invest getUserLike() {
		return UserLike;
	}

	public void setUserLike(Invest userLike) {
		UserLike = userLike;
	}



	private static long serialVersionUID = 1L;
	// Les attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int reactId;
	@Enumerated(EnumType.STRING)
	Reaction reaction;

	@ManyToOne
	Post PostLike;
	

	
	@ManyToOne
	Invest UserLike;
	

}
