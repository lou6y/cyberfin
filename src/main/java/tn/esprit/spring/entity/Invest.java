package tn.esprit.spring.entity;

import java.io.Serializable;
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

	@Column(name = "Interest_Rate")
	private double interest;

	@Column(name = "Invest_Start_Date")
	private LocalDateTime Investstart;

	@Column(name = "Invest_End_Date")
	private LocalDateTime Investend;

	@Column(name = "Email")
	private String Email;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		Invest.serialVersionUID = serialVersionUID;
	}

	public Invest(int userId, int amount, double interest, LocalDateTime investstart, LocalDateTime investend,
			String email) {
		super();

		this.userId = userId;
		this.amount = amount;
		this.interest = interest;
		Investstart = investstart;
		Investend = investend;
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

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public LocalDateTime getInveststart() {
		return Investstart;
	}

	public void setInveststart(LocalDateTime investstart) {
		Investstart = investstart;
	}

	public LocalDateTime getInvestend() {
		return Investend;
	}

	public void setInvestend(LocalDateTime investend) {
		Investend = investend;
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
