package tn.esprit.spring.DAO.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Role implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long idRole;
	@Enumerated(EnumType.STRING)
	private RoleName role;
	public Role() {
		super();
	}
	public Role(Long idRole, RoleName role) {
		super();
		this.idRole = idRole;
		this.role = role;
	}
	public Long getIdRole() {
		return idRole;
	}
	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	public String getRole() {
		return role.toString();
	}
	public void setRole(RoleName role) {
		this.role = role;
	}
	
}
