package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.*;

@Entity
@Table(name = "Invest")

public class Invest implements Serializable {
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;

	@Column(name = "Amount")
	private int amount;

	

	@Column(name = "Invest_Start_Date")
	private LocalDate Investstart;

	

	@Column(name = "Email")
	private String Email;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		Invest.serialVersionUID = serialVersionUID;
	}

	public Invest(int userId, int amount, LocalDate investstart,
			String email) {
		super();

		this.userId = userId;
		this.amount = amount;
		
		Investstart = investstart;
		
		Email = email;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}




	public LocalDate getInveststart() {
		return Investstart;
	}

	public void setInveststart(LocalDate investstart) {
		Investstart = investstart;
	}


	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public List<React> getLikes() {
		return likes;
	}

	public void setLikes(List<React> likes) {
		this.likes = likes;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "UserLike")
	private List<React> likes;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Post> posts;

	public Invest() {
		super();
		// TODO Auto-generated constructor stub
	}



}
