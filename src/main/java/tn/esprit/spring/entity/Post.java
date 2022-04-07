package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity

public class Post implements Serializable {
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", text=" + text + ", date=" + date + ", Nlikes=" + Nlikes + "]";
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		Post.serialVersionUID = serialVersionUID;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNlikes() {
		return Nlikes;
	}

	public void setNlikes(int nlikes) {
		Nlikes = nlikes;
	}

	public List<React> getLikes() {
		return likes;
	}

	public void setLikes(List<React> likes) {
		this.likes = likes;
	}

	public Invest getUser() {
		return user;
	}

	public void setUser(Invest user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Post> getChildren() {
		return children;
	}

	public void setChildren(List<Post> children) {
		this.children = children;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	private String text;
	@Temporal(TemporalType.DATE)
	private Date date;
	private int Nlikes;
	

	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="PostLike")
	private List<React> likes; 
	@ManyToOne
	private Invest user;
	
	@ManyToOne
    private Post post;

    @OneToMany(mappedBy = "post")
    private List<Post> children = new ArrayList<Post>();


	}

	