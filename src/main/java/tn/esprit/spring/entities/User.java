package tn.esprit.spring.DAO.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String email;
	private String password;
	private String name;
	private String lastname;
	@Temporal (TemporalType.DATE)
	private Date dateBirth;
	private String gender;
	@Enumerated(EnumType.STRING)
	private Job job;
	private String adress;
	@Column(nullable = true, length = 64)
	private String profilePic;
	@Column(nullable = true, length = 64)
	private String identitycardPic;
	@Column(nullable = true, length = 64)
	private String signaturePic;
	@Temporal (TemporalType.DATE)
	private Date dateCreation;
	private Boolean active;
	private Boolean verified;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch
			= FetchType.EAGER)
			private Set<Role> roles;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Offer> Offers;
	
	public User() {
		super();
	}
	
	public User(Long id, String userName, String email, String password, String name, String lastname,
			Set<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.roles = roles;
	}

	public User(Long id, String userName, String email, String password, String name, String lastname, Date dateBirth,
			String gender, Job job, String adress, String profilePic, String identitycardPic, String signaturePi,
			Date dateCreation, Boolean verified, Set<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.dateBirth = dateBirth;
		this.gender = gender;
		this.job = job;
		this.adress = adress;
		this.profilePic = profilePic;
		this.identitycardPic = identitycardPic;
		this.signaturePic = signaturePi;
		this.dateCreation = dateCreation;
		this.verified = verified;
		this.roles = roles;
		
		
		
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getIdentitycardPic() {
		return identitycardPic;
	}
	public void setIdentitycardPic(String identitycardPic) {
		this.identitycardPic = identitycardPic;
	}
	public String getSignaturePic() {
		return signaturePic;
	}
	public void setSignaturePic(String signaturePi) {
		this.signaturePic = signaturePi;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@OneToOne
	private Account account;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	





}
